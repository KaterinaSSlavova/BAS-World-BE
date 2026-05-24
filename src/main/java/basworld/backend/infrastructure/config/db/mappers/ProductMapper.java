package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.type.Type;
import basworld.backend.infrastructure.config.db.entity.BrandEntity;
import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import basworld.backend.infrastructure.config.db.entity.ProductEntity;
import basworld.backend.infrastructure.config.db.entity.TypeEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        return Product.builder()
                .id(entity.getId())
                .sku(entity.getSku())
                .name(entity.getName())
                .description(entity.getDescription())
                .brand(BrandMapper.toDomain(entity.getBrand()))
                .status(entity.getStatus())
                .type(TypeMapper.toDomain(entity.getType()))
                .category(CategoryMapper.toDomain(entity.getCategory()))
                .vehicleType(VehicleTypeMapper.toDomain(entity.getVehicleType()))
                .build();
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null) return null;

        return ProductEntity.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .brand(BrandMapper.toEntity(product.getBrand()))
                .status(product.getStatus())
                .type(TypeMapper.toEntity(product.getType()))
                .category(CategoryMapper.toEntity(product.getCategory()))
                .vehicleType(VehicleTypeMapper.toEntity(product.getVehicleType()))
                .build();
    }
}