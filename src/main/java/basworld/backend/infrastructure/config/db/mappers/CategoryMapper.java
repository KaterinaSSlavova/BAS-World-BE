package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.category.Category;
import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static Category toDomain(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        Category parent = null;

        if (entity.getParent() != null) {
            parent = Category.builder()
                    .id(entity.getParent().getId())
                    .name(entity.getParent().getName())
                    .parent(null)
                    .build();
        }

        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .parent(parent)
                .build();
    }

    public static CategoryEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }

        CategoryEntity parent = null;

        if (category.getParent() != null) {
            parent = CategoryEntity.builder()
                    .id(category.getParent().getId())
                    .name(category.getParent().getName())
                    .build();
        }

        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .parent(parent)
                .build();
    }
}