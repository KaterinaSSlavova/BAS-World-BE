package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.category.Category;
import basworld.backend.infrastructure.config.db.entity.CategoryEntity;

public class CategoryMapper {
    public static CategoryEntity toEntity(Category category) {
        if (category == null) return null;
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .parent(category.getParent() != null
                        ? CategoryEntity.builder()
                        .id(category.getParent().getId())
                        .build()
                        : null)
                .build();
    }
    public static Category fromEntity(CategoryEntity entity) {
        if (entity == null|| entity.getId() == null) return null;
        return new Category(entity.getId(), entity.getName(), fromEntity(entity.getParent()));
    }

}
