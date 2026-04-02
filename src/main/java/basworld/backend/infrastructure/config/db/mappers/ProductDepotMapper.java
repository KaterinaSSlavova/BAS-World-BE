package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import basworld.backend.infrastructure.config.db.entity.ProductDepotId;

public class ProductDepotMapper {

    public static ProductDepotEntity toEntity(ProductDepot productDepot) {
        if (productDepot == null) {
            return null;
        }

        ProductDepotEntity entity = ProductDepotEntity.builder()
                .product(ProductMapper.toEntity(productDepot.getProduct()))
                .depot(DepotMapper.toEntity(productDepot.getDepot()))
                .isAvailable(productDepot.isAvailable())
                .stockQuantity(productDepot.getStockQuantity())
                .build();


        if (productDepot.getProduct() != null && productDepot.getDepot() != null) {
            entity.setId(new ProductDepotId(
                    productDepot.getProduct().getId(),
                    productDepot.getDepot().getId()
            ));
        }

        return entity;
    }

    public static ProductDepot toDomain(ProductDepotEntity entity) {
        if (entity == null) {
            return null;
        }

        return ProductDepot.builder()
                .product(ProductMapper.toDomain(entity.getProduct()))
                .depot(DepotMapper.toDomain(entity.getDepot()))
                .isAvailable(entity.getIsAvailable())
                .stockQuantity(entity.getStockQuantity())
                .build();
    }
}