package basworld.backend.domain.repository;

import basworld.backend.domain.type.Type;

import java.util.List;
import java.util.Optional;

public interface TypeRepository {
    Optional<Type> findById(Long id);
    List<Type> findAll();
    List<Type> findAllRoots();
    Type save(Type type);
    void delete(Type type);
    boolean existsByNameAndIsArchivedFalse(String name);

}
