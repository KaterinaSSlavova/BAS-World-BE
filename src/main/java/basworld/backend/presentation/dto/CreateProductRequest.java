package basworld.backend.presentation.dto;

import basworld.backend.domain.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor @Data
public class CreateProductRequest {
    private String sku;
    private String name;
    private String description;
    private Long brandId;
    private BigDecimal price;
    private ProductStatus status;
    private Long typeId;
    private Long categoryId;

    //depot
    List<ProductDepotRequest> productDepots;
}

