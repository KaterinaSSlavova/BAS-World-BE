package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import basworld.backend.infrastructure.config.db.entity.ProductDepotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductInsightsJPARepository extends JpaRepository<ProductDepotEntity, ProductDepotId> {
    @Query("""
    SELECT COUNT(DISTINCT pd.product.id)
    FROM ProductDepotEntity pd
    WHERE pd.depot.id = :depotId
""")
    long countProductsByDepotId(Long depotId);

    @Query("""
    SELECT COUNT(DISTINCT pd.product.id)
    FROM ProductDepotEntity pd
    WHERE pd.depot.id = :depotId
      AND pd.stockQuantity < 5
""")
    long countLowStockProductsByDepotId(Long depotId);

    @Query("""
    SELECT COUNT(pd)
    FROM ProductDepotEntity pd
    WHERE pd.depot.id = :depotId
      AND pd.isAvailable = false
""")
    long countUnavailableItemsByDepotId(Long depotId);

    @Query("""
    SELECT COALESCE(SUM(pd.stockQuantity * p.price), 0)
    FROM ProductDepotEntity pd
    JOIN pd.product p
    WHERE pd.depot.id = :depotId
""")
    java.math.BigDecimal sumInventoryValueByDepotId(Long depotId);
}
