package basworld.backend.infrastructure.config.db.implementation;

import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;
import basworld.backend.infrastructure.config.db.mappers.TypeMapper;
import basworld.backend.infrastructure.config.db.repository.jpaTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class TypeRepositoryImpl implements TypeRepository {
    private jpaTypeRepository repository;
    public Optional<Type> findById(Long id){
        return repository.findById(id).map(TypeMapper::fromEntity);
    }
}
