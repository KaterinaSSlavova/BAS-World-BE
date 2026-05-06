package basworld.backend.brand;

import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
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
public class BrandControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    void createBrand_withValidData_shouldReturnCreated() throws Exception {
        // arrange
        String body = """
                {
                    "name": "Michelin"
                }
                """;

        //act+assert
        mockMvc.perform(post("/api/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Michelin"));
    }

    @Test
    void createBrand_withInvalidRequest_returnsBadRequest() throws Exception {
        // arrange
        String body = """
                {
                    "name": ""
                }
                """;

        // act + assert
        mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBrand_withValidData_shouldReturnUpdatedBrand() throws Exception {
        Brand brand = Brand.builder().name("Old brand").archived(false).build();
        Brand savedBrand = brandRepository.saveBrand(brand);
        String body = """
                {
                    "name": "Updated brand"
                }
        """;

        // act + assert
        mockMvc.perform(put("/api/brands/" + savedBrand.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedBrand.getId()))
                .andExpect(jsonPath("$.name").value("Updated brand"));
    }

    @Test
    void archiveBrand_withExistingId_returnsNoContent() throws Exception {
        // arrange
        Brand brand = Brand.builder().name("Brand").archived(false).build();
        Brand savedBrand = brandRepository.saveBrand(brand);

        // act + assert
        mockMvc.perform(put("/api/brands/" + savedBrand.getId() + "/archive"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllBrands_returnsBrands() throws Exception {
        // arrange
        Brand brand = Brand.builder().name("Brand").archived(false).build();
        Brand savedBrand = brandRepository.saveBrand(brand);

        // act + assert
        mockMvc.perform(get("/api/brands"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItem("Brand")));
    }

    @Test
    void getBrandById_withExistingId_returnsBrand() throws Exception {
        // arrange
        Brand brand = Brand.builder().name("Brand").archived(false).build();
        Brand savedBrand = brandRepository.saveBrand(brand);

        // act + assert
        mockMvc.perform(get("/api/brands/" + savedBrand.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedBrand.getId()))
                .andExpect(jsonPath("$.name").value("Brand"));
    }
}
