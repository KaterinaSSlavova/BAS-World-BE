package basworld.backend.type;

import basworld.backend.domain.type.Type;
import basworld.backend.domain.repository.TypeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class TypeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TypeRepository typeRepository;

    @Test
    void createType_withValidData_shouldReturnCreated() throws Exception {
        // arrange
        String body = """
                {
                    "name": "Summer"
                }
                """;

        // act + assert
        mockMvc.perform(post("/api/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Summer"));
    }

    @Test
    void createType_withParentId_shouldReturnCreated() throws Exception {
        // arrange
        Type parent = Type.builder().name("Parent Type").isArchived(false).build();
        Type savedParent = typeRepository.save(parent);

        String body = """
                {
                    "name": "Child Type",
                    "parentId": %d
                }
                """.formatted(savedParent.getId());

        // act + assert
        mockMvc.perform(post("/api/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Child Type"));
    }

    @Test
    void createType_withInvalidRequest_returnsBadRequest() throws Exception {
        // arrange
        String body = """
                {
                    "name": ""
                }
                """;

        // act + assert
        mockMvc.perform(post("/api/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateType_withValidData_shouldReturnUpdatedType() throws Exception {
        // arrange
        Type type = Type.builder().name("Old Type").isArchived(false).build();
        Type savedType = typeRepository.save(type);

        String body = """
                {
                    "name": "Updated Type"
                }
                """;

        // act + assert
        mockMvc.perform(put("/api/types/" + savedType.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedType.getId()))
                .andExpect(jsonPath("$.name").value("Updated Type"));
    }

    @Test
    void updateType_withParentId_shouldReturnUpdatedType() throws Exception {
        // arrange
        Type parent = Type.builder().name("Parent Type").isArchived(false).build();
        Type savedParent = typeRepository.save(parent);

        Type type = Type.builder().name("Old Type").isArchived(false).build();
        Type savedType = typeRepository.save(type);

        String body = """
                {
                    "name": "Updated Type",
                    "parentId": %d
                }
                """.formatted(savedParent.getId());

        // act + assert
        mockMvc.perform(put("/api/types/" + savedType.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedType.getId()))
                .andExpect(jsonPath("$.name").value("Updated Type"));
    }

    @Test
    void archiveType_withExistingId_returnsNoContent() throws Exception {
        // arrange
        Type type = Type.builder().name("Type").isArchived(false).build();
        Type savedType = typeRepository.save(type);

        // act + assert
        mockMvc.perform(put("/api/types/" + savedType.getId() + "/archive"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllTypes_returnsTypes() throws Exception {
        // arrange
        Type type = Type.builder().name("Type").isArchived(false).build();
        typeRepository.save(type);

        // act + assert
        mockMvc.perform(get("/api/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItem("Type")));
    }

    @Test
    void getTypeById_withExistingId_returnsType() throws Exception {
        // arrange
        Type type = Type.builder().name("Type").isArchived(false).build();
        Type savedType = typeRepository.save(type);

        // act + assert
        mockMvc.perform(get("/api/types/" + savedType.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedType.getId()))
                .andExpect(jsonPath("$.name").value("Type"));
    }
}