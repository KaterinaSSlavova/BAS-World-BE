package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaVehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Long> {
    boolean existsByNameAndArchivedFalse(String name);
}
