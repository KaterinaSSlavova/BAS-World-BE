package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.presentation.dto.product.ProductWithDepotsResponse;

import java.util.List;

public class ProductWithDepotsDtoMapper {
    public static ProductWithDepotsResponse toResponse(Product product, List<ProductDepot> productDepots) {
        return ProductWithDepotsResponse.builder()
                .product(ProductDtoMapper.toProductPublicData(product))
                .depots(productDepots.stream()
                        .map(ProductDepotDtoMapper::toResponse)
                        .toList())
                .build();
    }
}
