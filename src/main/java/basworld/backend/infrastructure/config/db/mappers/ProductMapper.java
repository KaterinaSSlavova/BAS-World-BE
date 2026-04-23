package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.type.Type;
import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import basworld.backend.infrastructure.config.db.entity.ProductEntity;
import basworld.backend.infrastructure.config.db.entity.TypeEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        Type type = TypeMapper.toDomain(entity.getType());
        Category category = CategoryMapper.toDomain(entity.getCategory());

        return Product.builder()
                .id(entity.getId())
                .sku(entity.getSku())
                .name(entity.getName())
                .description(entity.getDescription())
                .brand(entity.getBrand())
                .price(entity.getPrice())
                .status(entity.getStatus())
                .type(type)
                .category(category)
                .build();
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        TypeEntity typeEntity = TypeMapper.toEntity(product.getType());
        CategoryEntity categoryEntity = CategoryMapper.toEntity(product.getCategory());

        return ProductEntity.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .status(product.getStatus())
                .type(typeEntity)
                .category(categoryEntity)
                .build();
    }
}