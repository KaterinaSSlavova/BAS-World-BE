package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.presentation.dto.product.ProductDepotPublicData;


public class ProductDepotDtoMapper {
    public static ProductDepotPublicData toResponse(ProductDepot productDepot) {
        return ProductDepotPublicData.builder()
                .depot(DepotMapper.toResponse(productDepot.getDepot()))
                .stockQuantity(productDepot.getStockQuantity())
                .isAvailable(productDepot.isAvailable())
                .costPrice(productDepot.getCostPrice())
                .salePrice(productDepot.getSalePrice())
                .stockThreshold(productDepot.getStockThreshold())
                .build();
    }
}
