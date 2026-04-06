package basworld.backend.infrastructure.config.db.implementation;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import basworld.backend.infrastructure.config.db.mappers.CategoryMapper;
import basworld.backend.infrastructure.config.db.repository.jpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository @AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final jpaCategoryRepository jpaCategoryRepository;
    public Optional<Category> findById(Long id){
        return jpaCategoryRepository.findById(id).map(CategoryMapper::fromEntity);
    }
}
