package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import basworld.backend.infrastructure.config.db.entity.ProductDepotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDepotJPARepository extends JpaRepository<ProductDepotEntity, ProductDepotId> {

    @Query("""
        SELECT pd
        FROM ProductDepotEntity pd
        JOIN FETCH pd.product p
        JOIN FETCH pd.depot d
        LEFT JOIN FETCH p.type
        LEFT JOIN FETCH p.category
    """)
    List<ProductDepotEntity> findAllWithProductAndDepot();
}