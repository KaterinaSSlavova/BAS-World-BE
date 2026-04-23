package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaBrandRepository extends JpaRepository<BrandEntity,Long> {
}
