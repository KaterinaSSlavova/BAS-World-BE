package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import basworld.backend.infrastructure.config.db.entity.ProductDepotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

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
    SELECT COALESCE(SUM(pd.stockQuantity * pd.costPrice), 0)
    FROM ProductDepotEntity pd
    WHERE pd.depot.id = :depotId
""")
    java.math.BigDecimal sumInventoryValueByDepotId(Long depotId);

    @Query("""
        SELECT COUNT(DISTINCT pd.product.id)
        FROM ProductDepotEntity pd
    """)
    long countProductsOverall();

    @Query("""
            SELECT COUNT(DISTINCT pd.product.id)
            FROM ProductDepotEntity pd
            WHERE pd.stockQuantity <= pd.stockThreshold
            AND pd.product.status NOT IN ('Inactive', 'Archived')
    """)
    long countLowStockProductsOverall();

    @Query("""
        SELECT COUNT(pd)
        FROM ProductDepotEntity pd
        JOIN pd.product p
        WHERE pd.stockQuantity = 0
        AND p.status NOT IN ('Inactive', 'Archived')
    """)
    long countUnavailableItemsOverall();

    @Query("""
        SELECT COALESCE(SUM(stockQuantity * costPrice), 0)
        FROM ProductDepotEntity
    """)
    BigDecimal sumInventoryValueOverall();
}
