package basworld.backend.presentation.dto;


import java.math.BigDecimal;

public record ProductInsightsResponse(
            long totalProducts,
            long lowStockProducts,
            long unavailableItems,
            BigDecimal inventoryValue
    ) {
    }

