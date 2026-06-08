package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import basworld.backend.infrastructure.config.db.entity.ProductEntity;
import basworld.backend.infrastructure.config.db.projection.CategoryValueProjection;
import basworld.backend.infrastructure.config.db.projection.DepotInventoryValueProjection;
import basworld.backend.infrastructure.config.db.projection.DepotProductCountProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyticsJPARepository extends JpaRepository<ProductDepotEntity, Long> {

    @Query("SELECT p.category.id as categoryId, p.category.name as categoryName, SUM(pd.stockQuantity * pd.costPrice) as totalValue " +
            "FROM ProductDepotEntity pd " +
            "JOIN pd.product p " +
            "GROUP BY p.category.id, p.category.name")
    List<CategoryValueProjection> findStockValueByCategory();

    @Query("SELECT d.id as depotId, d.depotName as depotName, COUNT(p.id) as totalProducts " +
            "FROM ProductDepotEntity pd " +
            "JOIN pd.depot d " +
            "JOIN pd.product p " +
            "GROUP BY d.id, d.depotName")
    List<DepotProductCountProjection> countProductsByDepot();

    @Query("SELECT d.id as depotId, d.depotName as depotName, SUM(pd.stockQuantity * pd.costPrice) as totalValue " +
            "FROM ProductDepotEntity pd " +
            "JOIN pd.depot d " +
            "JOIN pd.product p " +
            "GROUP BY d.id, d.depotName")
    List<DepotInventoryValueProjection> findInventoryValueByDepot();

    @Query("SELECT pd.product " +
            "FROM ProductDepotEntity pd " +
            "JOIN pd.product p " +
            "ORDER BY pd.stockQuantity DESC")
    List<ProductEntity> findTopProductByQuantity(Pageable pageable);
}