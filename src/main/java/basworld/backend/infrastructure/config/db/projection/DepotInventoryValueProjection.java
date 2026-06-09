package basworld.backend.infrastructure.config.db.projection;

import java.math.BigDecimal;

public interface DepotInventoryValueProjection {
    Long getDepotId();
    String getDepotName();
    BigDecimal getTotalValue();
}
