package basworld.backend.presentation.dto.vehicleType;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class VehicleTypeResponse {
    Long id;
    String name;
    boolean archived;
}
