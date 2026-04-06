package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.ProductEntity;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.data.jpa.repository.JpaRepository;

@ReflectiveScan
public interface jpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
