package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface jpaBrandRepository extends JpaRepository<BrandEntity,Long> {
    boolean existsByBrandNameAndArchivedFalse(String name);
    List<BrandEntity> findAllByArchivedFalse();
}
