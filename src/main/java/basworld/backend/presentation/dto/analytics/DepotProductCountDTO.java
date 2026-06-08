package basworld.backend.presentation.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class DepotProductCountDTO {
    private Long depotId;
    private String depotName;
    private Long totalProducts;
}
