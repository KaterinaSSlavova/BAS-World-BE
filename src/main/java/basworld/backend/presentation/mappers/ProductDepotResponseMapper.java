package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.presentation.dto.ProductDepotResponse;

public class ProductDepotResponseMapper {

    public static ProductDepotResponse toResponse(ProductDepot productDepot) {

        Product product = productDepot.getProduct();

        return ProductDepotResponse.builder()
                .productId(product.getId())
                .sku(product.getSku())
                .productName(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand().getName())
                .status(product.getStatus())

                .type(product.getType() != null
                        ? product.getType().getName()
                        : null)
                .typeId(product.getType() != null
                        ? product.getType().getId()
                        : null)

                .category(product.getCategory() != null
                        ? product.getCategory().getName()
                        : null)
                .categoryId(product.getCategory() != null
                        ? product.getCategory().getId()
                        : null)
                .depotId(productDepot.getDepot().getId())

                .depotName(productDepot.getDepot().getDepotName())
                .stockQuantity(productDepot.getStockQuantity())
                .available(productDepot.isAvailable())
                .build();
    }
}