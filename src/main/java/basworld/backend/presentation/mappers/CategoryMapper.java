package basworld.backend.presentation.mappers;

import basworld.backend.domain.category.Category;
import basworld.backend.presentation.dto.category.CategoryRequest;
import basworld.backend.presentation.dto.category.CategoryResponse;

public class CategoryMapper {

    public static CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .isArchived(category.isArchived())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .parentName(category.getParent() != null ? category.getParent().getName() : null)
                .build();
    }

    public static Category toDomain(CategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .isArchived(false)
                .parent(null)
                .build();
    }
}