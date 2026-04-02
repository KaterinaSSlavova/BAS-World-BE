package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaProductDepotRepository extends JpaRepository<ProductDepotEntity, Long> {
    boolean existsByProductIdAndDepotId(Long productId, Long depotId);
}
