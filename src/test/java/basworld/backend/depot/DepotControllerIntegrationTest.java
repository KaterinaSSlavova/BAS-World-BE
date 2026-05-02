package basworld.backend.depot;

import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DepotControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepotRepository depotRepository;

    @Test
    void createDepot_withValidData_shouldReturnCreated() throws Exception {
        String body = """
                {
                    "depotName": "New Depot",
                    "location": "Location"
                }
                """;

        mockMvc.perform(post("/api/depots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.depotName").value("New Depot"))
                .andExpect(jsonPath("$.location").value("Location"));
    }

    @Test
    void createDepot_withInvalidData_shouldReturnBadRequest() throws Exception {
        String body = """
                {
                    "depotName": "",
                    "location": ""
                }
                """;

        mockMvc.perform(post("/api/depots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getDepotById_withExistingId_shouldReturnDepot() throws Exception {
        Depot depot = Depot.builder().depotName("Tilburg Depot").location("Tilburg").archived(false).build();
        Depot savedDepot = depotRepository.saveDepot(depot);

        mockMvc.perform(get("/api/depots/" + savedDepot.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedDepot.getId()))
                .andExpect(jsonPath("$.depotName").value("Tilburg Depot"))
                .andExpect(jsonPath("$.location").value("Tilburg"));
    }

    @Test
    void getDepotOverview_shouldReturnDepots() throws Exception {
        Depot depot = Depot.builder().depotName("Breda Depot").location("Breda").archived(false).build();
        depotRepository.saveDepot(depot);

        mockMvc.perform(get("/api/depots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.depots[*].depotName").value(hasItem("Breda Depot")))
                .andExpect(jsonPath("$.depots[*].location").value(hasItem("Breda")));
    }

    @Test
    void updateDepot_withValidData_shouldReturnUpdatedDepot() throws Exception {
        Depot depot = Depot.builder().depotName("Old Depot").location("Old Location").archived(false).build();
        Depot savedDepot = depotRepository.saveDepot(depot);

        String body = """
                {
                    "depotName": "Updated Depot",
                    "location": "Updated Location"
                }
                """;

        mockMvc.perform(put("/api/depots/" + savedDepot.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedDepot.getId()))
                .andExpect(jsonPath("$.depotName").value("Updated Depot"))
                .andExpect(jsonPath("$.location").value("Updated Location"));
    }

    @Test
    void archiveDepot_withExistingId_shouldReturnNoContent() throws Exception {
        Depot depot = Depot.builder().depotName("Archive Depot").location("Amsterdam").archived(false).build();
        Depot savedDepot = depotRepository.saveDepot(depot);

        mockMvc.perform(put("/api/depots/" + savedDepot.getId() + "/archive"))
                .andExpect(status().isNoContent());
    }
}