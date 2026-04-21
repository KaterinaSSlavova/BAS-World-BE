package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
