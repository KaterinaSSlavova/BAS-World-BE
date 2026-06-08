package basworld.backend.domain.analytics;

import basworld.backend.domain.product.Product;

import java.util.List;

public record AnalyticsData(List<CategoryValue> stockValueByCategory,
                            List<DepotProductCount> productCountByDepot,
                            List<DepotInventoryValue> inventoryValueByDepot,
                            Product highestQuantityProduct) {
}
