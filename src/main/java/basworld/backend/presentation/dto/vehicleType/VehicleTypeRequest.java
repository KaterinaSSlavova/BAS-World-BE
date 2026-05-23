package basworld.backend.presentation.dto.vehicleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class VehicleTypeRequest {
    String name;
    boolean archived;
}
