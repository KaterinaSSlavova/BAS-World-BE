package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.product.Product;
import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import basworld.backend.infrastructure.config.db.entity.ProductEntity;
import basworld.backend.infrastructure.config.db.entity.TypeEntity;

public class ProductMapper {

    public static ProductEntity toEntity(Product product){
        return ProductEntity.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .status(product.getStatus())
                .type(TypeMapper.toEntity(product.getType()))
                .category(CategoryMapper.toEntity(product.getCategory()))
                .build();
    }
    public static Product fromEntity(ProductEntity entity){
        return new Product(entity.getId(), entity.getSku(),entity.getName(), entity.getDescription(), entity.getBrand(), entity.getPrice(),
                entity.getStatus(), TypeMapper.fromEntity(entity.getType()), CategoryMapper.fromEntity(entity.getCategory()));

    }
}
