package basworld.backend.domain.analytics;

import java.math.BigDecimal;

public record CategoryValue(Long categoryId, String categoryName, BigDecimal totalValue) {
}
