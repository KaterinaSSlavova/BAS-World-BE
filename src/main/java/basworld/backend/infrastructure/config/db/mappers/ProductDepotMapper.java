package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import basworld.backend.infrastructure.config.db.entity.ProductDepotId;

public class ProductDepotMapper {
    public static ProductDepot toDomain(ProductDepotEntity entity) {
        return ProductDepot.builder()
                .product(ProductMapper.toDomain(entity.getProduct()))
                .depot(DepotMapper.toDomain(entity.getDepot()))
                .stockQuantity(entity.getStockQuantity())
                .costPrice(entity.getCostPrice())
                .salePrice(entity.getSalePrice())
                .stockThreshold(entity.getStockThreshold())
                .supplier(SupplierMapper.toDomain(entity.getSupplier()))
                .build();
    }

    public static ProductDepotEntity toEntity(ProductDepot domain) {
        return ProductDepotEntity.builder()
                .id(new ProductDepotId(domain.getProduct().getId(), domain.getDepot().getId()))
                .product(ProductMapper.toEntity(domain.getProduct()))
                .depot(DepotMapper.toEntity(domain.getDepot()))
                .isAvailable(domain.isAvailable())
                .stockQuantity(domain.getStockQuantity())
                .costPrice(domain.getCostPrice())
                .salePrice(domain.getSalePrice())
                .stockThreshold(domain.getStockThreshold())
                .supplier(SupplierMapper.toEntity(domain.getSupplier()))
                .build();
    }
}
