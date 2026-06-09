package basworld.backend.domain.analytics;

import java.math.BigDecimal;

public record DepotInventoryValue(Long depotId, String depotName, BigDecimal totalValue) {
}
