package basworld.backend.domain.repository;

import basworld.backend.domain.type.Type;

import java.util.Optional;

public interface TypeRepository {
    Optional<Type> findById(Long id);
}
