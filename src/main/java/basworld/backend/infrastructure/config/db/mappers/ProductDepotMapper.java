package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;

public class ProductDepotMapper {
    public static ProductDepotEntity toEntity(ProductDepot productDepot) {
        return ProductDepotEntity
                //product mapping
                .builder().depot(DepotMapper.ToEntity(productDepot.getDepot()))
                .isAvailable(productDepot.isAvailable())
                .stockQuantity(productDepot.getStockQuantity())
                .build();
    }

    public static ProductDepot toDomain(ProductDepotEntity productDepotEntity) {
        return ProductDepot.builder()
                //.product()
                .depot(DepotMapper.ToDomain(productDepotEntity.getDepot()))
                .isAvailable(productDepotEntity.getIsAvailable())
                .stockQuantity(productDepotEntity.getStockQuantity())
                .build();
    }
}
