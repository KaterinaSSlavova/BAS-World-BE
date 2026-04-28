package basworld.backend.business.useCase.category;

import basworld.backend.domain.category.Category;

public interface UpdateCategoryUseCase {
    Category updateCategory(Long id, Category category, Long parentId);
}