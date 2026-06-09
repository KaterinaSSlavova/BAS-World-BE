package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface jpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByIsArchivedFalse();
    boolean existsByNameAndIsArchivedFalse(String name);
}
