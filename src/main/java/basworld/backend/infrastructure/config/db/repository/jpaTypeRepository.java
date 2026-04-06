package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaTypeRepository extends JpaRepository<TypeEntity,Long> {
}
