package basworld.backend.domain.repository;

import basworld.backend.domain.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Long id);
    List<Category> findAll();
    List<Category> findAllRoots();
    Category save(Category category);
    void delete(Category category);
    boolean existsByNameAndIsArchivedFalse(String name);

    //Category saveCategory(Category parent);
}
