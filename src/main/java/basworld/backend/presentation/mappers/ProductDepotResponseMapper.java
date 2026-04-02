package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.presentation.dto.ProductDepotResponse;

public class ProductDepotResponseMapper {

    public static ProductDepotResponse toResponse(ProductDepot productDepot) {
        return ProductDepotResponse.builder()
                .productId(productDepot.getProduct().getId())
                .sku(productDepot.getProduct().getSku())
                .productName(productDepot.getProduct().getName())
                .description(productDepot.getProduct().getDescription())
                .brand(productDepot.getProduct().getBrand())
                .price(productDepot.getProduct().getPrice())
                .status(productDepot.getProduct().getStatus())
                .type(productDepot.getProduct().getType() != null
                        ? productDepot.getProduct().getType().getName()
                        : null)
                .category(productDepot.getProduct().getCategory() != null
                        ? productDepot.getProduct().getCategory().getName()
                        : null)
                .depotName(productDepot.getDepot().getName())
                .stockQuantity(productDepot.getStockQuantity())
                .isAvailable(productDepot.isAvailable())
                .build();
    }
}