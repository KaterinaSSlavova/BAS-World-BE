package basworld.backend.domain.product;

import java.math.BigDecimal;

public record ProductInsights(
        long totalProducts,
        long lowStockProducts,
        long unavailableItems,
        BigDecimal inventoryValue
) {}
