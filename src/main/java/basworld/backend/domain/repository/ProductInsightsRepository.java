package basworld.backend.domain.repository;

import java.math.BigDecimal;

public interface ProductInsightsRepository {

    long countProductsByDepotId(Long depotId);

    long countLowStockProductsByDepotId(Long depotId);

    long countUnavailableItemsByDepotId(Long depotId);

    BigDecimal sumInventoryValueByDepotId(Long depotId);

    long countProductsOverall();
    long countLowStockProductsOverall();
    long countUnavailableItemsOverall();
    BigDecimal sumInventoryValueOverall();
}
