package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.DepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepotRepository extends JpaRepository<DepotEntity, Long> {
}
