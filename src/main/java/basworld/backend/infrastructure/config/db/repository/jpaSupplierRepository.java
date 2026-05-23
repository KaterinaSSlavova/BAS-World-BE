package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaSupplierRepository extends JpaRepository<SupplierEntity,Long> {
}
