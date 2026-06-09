package basworld.backend.infrastructure.config.db.projection;

import java.math.BigDecimal;

public interface CategoryValueProjection {
    Long getCategoryId();
    String getCategoryName();
    BigDecimal getTotalValue();
}
