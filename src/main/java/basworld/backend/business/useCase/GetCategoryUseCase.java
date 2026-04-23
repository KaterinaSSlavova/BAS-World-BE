package basworld.backend.business.useCase;

import basworld.backend.domain.category.Category;

public interface GetCategoryUseCase {
    Category findById(Long id);
}
