package basworld.backend.domain.repository;

import basworld.backend.domain.category.Category;

import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Long id);
}
