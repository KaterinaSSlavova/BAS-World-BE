package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface jpaSupplierRepository extends JpaRepository<SupplierEntity,Long> {
    boolean existsByNameAndArchivedFalse(String name);
    List<SupplierEntity> findAllByIsArchivedFalse();
}
