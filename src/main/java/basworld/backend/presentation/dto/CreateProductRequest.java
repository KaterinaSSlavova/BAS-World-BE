package basworld.backend.presentation.dto;

import basworld.backend.domain.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor @Data
public class CreateProductRequest {
    private String sku;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private ProductStatus status;
    private Long typeId;
    private Long categoryId;

    //depot
    private Long depotId;
    private Long stockQuantity;
    private boolean available;
}

