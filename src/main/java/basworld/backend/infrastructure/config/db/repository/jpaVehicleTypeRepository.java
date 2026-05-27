package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface jpaVehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Long> {
    boolean existsByNameAndArchivedFalse(String name);
    List<VehicleTypeEntity> findAllByArchivedFalse();
}
