package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface jpaTypeRepository extends JpaRepository<TypeEntity,Long> {
    List<TypeEntity> findAllByIsArchivedFalse();
    List<TypeEntity> findAllByParentIsNullAndIsArchivedFalse();
    boolean existsByNameAndIsArchivedFalse(String name);
}
