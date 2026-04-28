package basworld.backend.business.useCase.category;

import basworld.backend.domain.category.Category;

import java.util.List;

public interface GetAllCategoriesUseCase {
    List<Category> getAllCategories();
}
