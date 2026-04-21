package basworld.backend.presentation.mappers;

import basworld.backend.domain.category.Category;
import basworld.backend.presentation.dto.CategoryPublicData;

public class CategoryDtoMapper {
    public static CategoryPublicData toCategoryPublicData(Category category) {
        if (category == null) { return null; }
        return new CategoryPublicData(category.getId(), category.getName(), CategoryDtoMapper.toCategoryPublicData(category.getParent()));
    }
}
