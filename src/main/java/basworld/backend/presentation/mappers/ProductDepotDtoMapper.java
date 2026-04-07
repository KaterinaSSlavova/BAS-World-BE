package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.presentation.dto.ProductDepotPublicData;


public class ProductDepotDtoMapper {
    public static ProductDepotPublicData toResponse(ProductDepot productDepot) {
        return ProductDepotPublicData.builder()
                .product(ProductDtoMapper.toProductPublicData(productDepot.getProduct()))
                .depot(DepotMapper.toResponse(productDepot.getDepot()))
                .stockQuantity(productDepot.getStockQuantity())
                .isAvailable(productDepot.isAvailable())
                .build();
    }
}
