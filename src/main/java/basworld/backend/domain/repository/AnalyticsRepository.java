package basworld.backend.domain.repository;

import basworld.backend.domain.analytics.CategoryValue;
import basworld.backend.domain.analytics.DepotInventoryValue;
import basworld.backend.domain.analytics.DepotProductCount;
import basworld.backend.domain.product.Product;

import java.util.List;

public interface AnalyticsRepository {
    List<CategoryValue> getStockValueByCategory();
    List<DepotProductCount> getProductCountByDepot();
    List<DepotInventoryValue> getInventoryValueByDepot();
    Product getHighestQuantityProduct();
}
