package basworld.backend.infrastructure.config.db.repository;

import basworld.backend.infrastructure.config.db.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<TypeEntity,Long> {
}
