package basworld.backend.presentation.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
public class DepotInventoryValueDTO {
    private Long depotId;
    private String depotName;
    private BigDecimal totalValue;
}
