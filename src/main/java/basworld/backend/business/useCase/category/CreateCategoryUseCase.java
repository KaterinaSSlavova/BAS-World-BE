package basworld.backend.business.useCase.category;

import basworld.backend.domain.category.Category;

public interface CreateCategoryUseCase {
    Category createCategory(Category category, Long parentId);
}
