package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import basworld.backend.infrastructure.config.db.mappers.CategoryMapper;
import basworld.backend.infrastructure.config.db.repository.jpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final jpaCategoryRepository jpaCategoryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id).map(CategoryMapper::toDomain);
    }
}
