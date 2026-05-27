package basworld.backend.presentation.controller;

import basworld.backend.domain.supplier.Supplier;
import basworld.backend.domain.repository.SupplierRepository;
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
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void createSupplier_withValidData_shouldReturnCreated() throws Exception {
        String body = """
                {
                    "name": "PartsCorp",
                    "picture": "partscorp_logo.png",
                    "archived": false
                }
                """;

        mockMvc.perform(post("/api/suppliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("PartsCorp"))
                .andExpect(jsonPath("$.picture").value("partscorp_logo.png"));
    }

    @Test
    void createSupplier_withInvalidRequest_returnsBadRequest() throws Exception {
        String body = """
                {
                    "name": ""
                }
                """;

        mockMvc.perform(post("/api/suppliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateSupplier_withValidData_shouldReturnUpdatedSupplier() throws Exception {
        Supplier supplier = new Supplier("Old Supplier", "old.png");
        Supplier savedSupplier = supplierRepository.saveSupplier(supplier);
        String body = """
                {
                    "name": "New Supplier",
                    "picture": "new.png",
                    "archived": false
                }
                """;

        mockMvc.perform(put("/api/suppliers/" + savedSupplier.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedSupplier.getId()))
                .andExpect(jsonPath("$.name").value("New Supplier"))
                .andExpect(jsonPath("$.picture").value("new.png"));
    }

    @Test
    void archiveSupplier_withExistingId_returnsNoContent() throws Exception {
        Supplier supplier = new Supplier("SupplierX", "x.png");
        Supplier savedSupplier = supplierRepository.saveSupplier(supplier);

        mockMvc.perform(put("/api/suppliers/" + savedSupplier.getId() + "/archive"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllSuppliers_returnsSuppliers() throws Exception {
        Supplier supplier = new Supplier("Global Logistics", "global.png");
        supplierRepository.saveSupplier(supplier);

        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItem("Global Logistics")));
    }

    @Test
    void getSupplierById_withExistingId_returnsSupplier() throws Exception {
        Supplier supplier = new Supplier("Local Parts", "local.png");
        Supplier savedSupplier = supplierRepository.saveSupplier(supplier);

        mockMvc.perform(get("/api/suppliers/" + savedSupplier.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedSupplier.getId()))
                .andExpect(jsonPath("$.name").value("Local Parts"));
    }
}