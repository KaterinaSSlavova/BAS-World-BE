package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;

import basworld.backend.infrastructure.config.db.entity.TypeEntity;
import basworld.backend.infrastructure.config.db.mappers.TypeMapper;
import basworld.backend.infrastructure.config.db.repository.jpaTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TypeRepositoryImpl implements TypeRepository {
    private final jpaTypeRepository jpaTypeRepository;


    @Override
    public Optional<Type> findById(Long id) {
        return jpaTypeRepository.findById(id).map(TypeMapper::toDomain);
    }

    @Override
    public List<Type> findAll() {
        return jpaTypeRepository.findAllByIsArchivedFalse()
                .stream()
                .map(TypeMapper::toDomain)
                .toList();
    }

    @Override
    public Type save(Type type) {
        TypeEntity parentEntity = null;

        if (type.getParent() != null) {
            parentEntity = jpaTypeRepository.findById(type.getParent().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Parent type not found with id: " + type.getParent().getId()));
        }

        TypeEntity entity = TypeEntity.builder()
                .id(type.getId())
                .name(type.getName())
                .isArchived(type.isArchived())
                .parent(parentEntity)
                .build();

        return TypeMapper.toDomain(jpaTypeRepository.save(entity));
    }
    @Override
    public void delete(Type type) {
        type.archive();
        jpaTypeRepository.save(TypeMapper.toEntity(type));
    }
    @Override
    public boolean existsByNameAndIsArchivedFalse(String name) {
        return jpaTypeRepository.existsByNameAndIsArchivedFalse(name);
    }
}
