package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;

import basworld.backend.infrastructure.config.db.entity.TypeEntity;
import basworld.backend.infrastructure.config.db.mappers.TypeMapper;
import basworld.backend.infrastructure.config.db.repository.jpaTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TypeRepositoryImpl implements TypeRepository {
    private final jpaTypeRepository jpaTypeRepository;

    @Override
    public Optional<Type> findById(Long id) {
        return jpaTypeRepository.findById(id).map(TypeMapper::toDomain);
    }
}
