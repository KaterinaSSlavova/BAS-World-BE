package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import basworld.backend.infrastructure.config.db.mappers.CategoryMapper;
import basworld.backend.infrastructure.config.db.repository.jpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final jpaCategoryRepository jpaCategoryRepository;


    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id).map(CategoryMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAllByArchivedFalse()
                .stream()
                .map(CategoryMapper::toDomain)
                .toList();
    }

    @Override
    public List<Category> findAllRoots() {
        return jpaCategoryRepository.findAllByParentIsNullAndIsArchivedFalse()
                .stream()
                .map(CategoryMapper::toDomain)
                .toList();
    }

    @Override
    public Category save(Category category) {
        CategoryEntity parentEntity = null;

        if (category.getParent() != null) {
            parentEntity = jpaCategoryRepository.findById(category.getParent().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Parent category not found with id: " + category.getParent().getId()));
        }

        CategoryEntity entity = CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .isArchived(category.isArchived())
                .parent(parentEntity)
                .build();

        return CategoryMapper.toDomain(jpaCategoryRepository.save(entity));
    }

    @Override
    public void delete(Category category) {
        category.archive();
        jpaCategoryRepository.save(CategoryMapper.toEntity(category));
    }
    @Override
    public boolean existsByNameAndIsArchivedFalse(String name) {
        return jpaCategoryRepository.existsByNameAndIsArchivedFalse(name);
    }
}
