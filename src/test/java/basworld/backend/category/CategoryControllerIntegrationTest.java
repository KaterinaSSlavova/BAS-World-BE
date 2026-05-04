package basworld.backend.category;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
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
public class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void createCategory_withValidData_shouldReturnCreated() throws Exception {
        // arrange
        String body = """
                {
                    "name": "Tyres"
                }
                """;

        // act + assert
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Tyres"));
    }

    @Test
    void createCategory_withParentId_shouldReturnCreated() throws Exception {
        // arrange
        Category parent = Category.builder().name("Parent Category").isArchived(false).build();
        Category savedParent = categoryRepository.save(parent);

        String body = """
                {
                    "name": "Child Category",
                    "parentId": %d
                }
                """.formatted(savedParent.getId());

        // act + assert
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Child Category"));
    }

    @Test
    void createCategory_withInvalidRequest_returnsBadRequest() throws Exception {
        // arrange
        String body = """
                {
                    "name": ""
                }
                """;

        // act + assert
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateCategory_withValidData_shouldReturnUpdatedCategory() throws Exception {
        // arrange
        Category category = Category.builder().name("Old Category").isArchived(false).build();
        Category savedCategory = categoryRepository.save(category);

        String body = """
                {
                    "name": "Updated Category"
                }
                """;

        // act + assert
        mockMvc.perform(put("/api/categories/" + savedCategory.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCategory.getId()))
                .andExpect(jsonPath("$.name").value("Updated Category"));
    }

    @Test
    void updateCategory_withParentId_shouldReturnUpdatedCategory() throws Exception {
        // arrange
        Category parent = Category.builder().name("Parent Category").isArchived(false).build();
        Category savedParent = categoryRepository.save(parent);

        Category category = Category.builder().name("Old Category").isArchived(false).build();
        Category savedCategory = categoryRepository.save(category);

        String body = """
                {
                    "name": "Updated Category",
                    "parentId": %d
                }
                """.formatted(savedParent.getId());

        // act + assert
        mockMvc.perform(put("/api/categories/" + savedCategory.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCategory.getId()))
                .andExpect(jsonPath("$.name").value("Updated Category"));
    }

    @Test
    void archiveCategory_withExistingId_returnsNoContent() throws Exception {
        // arrange
        Category category = Category.builder().name("Category").isArchived(false).build();
        Category savedCategory = categoryRepository.save(category);

        // act + assert
        mockMvc.perform(put("/api/categories/" + savedCategory.getId() + "/archive"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllCategories_returnsCategories() throws Exception {
        // arrange
        Category category = Category.builder().name("Category").isArchived(false).build();
        categoryRepository.save(category);

        // act + assert
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItem("Category")));
    }

    @Test
    void getCategoryById_withExistingId_returnsCategory() throws Exception {
        // arrange
        Category category = Category.builder().name("Category").isArchived(false).build();
        Category savedCategory = categoryRepository.save(category);

        // act + assert
        mockMvc.perform(get("/api/categories/" + savedCategory.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCategory.getId()))
                .andExpect(jsonPath("$.name").value("Category"));
    }
}