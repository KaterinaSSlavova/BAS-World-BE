package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
