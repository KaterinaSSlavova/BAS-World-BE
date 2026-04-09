package basworld.backend.presentation.dto;

import basworld.backend.domain.product.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductDepotResponse {
    private Long productId;
    private String sku;
    private String productName;
    private String description;
    private String brand;
    private BigDecimal price;
    private ProductStatus status;

    private String type;
    private Long typeId;

    private String category;
    private Long categoryId;

    private String depotName;
    private Long stockQuantity;
    private boolean isAvailable;
}