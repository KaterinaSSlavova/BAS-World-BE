package basworld.backend.presentation.dto.product;

import basworld.backend.domain.product.ProductStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor @Data
public class CreateProductRequest {
    @NotNull
    @NotEmpty
    private String sku;
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private Long brandId;
    @NotNull
    private ProductStatus status;
    @NotNull
    private Long typeId;
    @NotNull
    private Long categoryId;

    //depot
    List<ProductDepotRequest> productDepots;
}

