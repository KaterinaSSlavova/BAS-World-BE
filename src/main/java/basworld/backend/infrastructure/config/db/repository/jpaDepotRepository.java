package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.DepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface jpaDepotRepository extends JpaRepository<DepotEntity, Long> {
    boolean existsByDepotNameAndArchivedFalse(String name);
    Collection<DepotEntity> findByIdIn(List<Long> depotIdList);
}
