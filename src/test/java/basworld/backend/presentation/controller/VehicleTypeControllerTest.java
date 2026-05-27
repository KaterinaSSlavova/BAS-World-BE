package basworld.backend.presentation.controller;
import basworld.backend.domain.vehicleType.VehicleType;
import basworld.backend.domain.repository.VehicleTypeRepository;
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
public class VehicleTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Test
    void createVehicleType_withValidData_shouldReturnCreated() throws Exception {
        String body = """
                {
                    "name": "new Tractor",
                    "archived": false
                }
                """;

        mockMvc.perform(post("/api/vehicle-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("new Tractor"));
    }

    @Test
    void createVehicleType_withInvalidRequest_returnsBadRequest() throws Exception {
        String body = """
                {
                    "name": ""
                }
                """;

        mockMvc.perform(post("/api/vehicle-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateVehicleType_withValidData_shouldReturnUpdatedType() throws Exception {
        VehicleType type = new VehicleType("Van", false);
        VehicleType savedType = vehicleTypeRepository.saveVehicleType(type);
        String body = """
                {
                    "name": "Heavy Van",
                    "archived" : false
                }
                """;

        mockMvc.perform(put("/api/vehicle-types/" + savedType.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedType.getId()))
                .andExpect(jsonPath("$.name").value("Heavy Van"));
    }

    @Test
    void archiveVehicleType_withExistingId_returnsNoContent() throws Exception {
        VehicleType type = new VehicleType("Trailer", false);
        VehicleType savedType = vehicleTypeRepository.saveVehicleType(type);

        mockMvc.perform(put("/api/vehicle-types/" + savedType.getId() + "/archive"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllVehicleTypes_returnsVehicleTypes() throws Exception {
        VehicleType type = new VehicleType("Bus", false);
        vehicleTypeRepository.saveVehicleType(type);

        mockMvc.perform(get("/api/vehicle-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItem("Bus")));
    }

    @Test
    void getVehicleTypeById_withExistingId_returnsVehicleType() throws Exception {
        VehicleType type = new VehicleType("Machinery", false);
        VehicleType savedType = vehicleTypeRepository.saveVehicleType(type);

        mockMvc.perform(get("/api/vehicle-types/" + savedType.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedType.getId()))
                .andExpect(jsonPath("$.name").value("Machinery"));
    }
}