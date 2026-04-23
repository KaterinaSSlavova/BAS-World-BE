package basworld.backend.presentation.dto;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.product.ProductStatus;
import basworld.backend.domain.type.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {

    @NotBlank
    private String name;

    private String description;

    private String brand;

    @NotNull
    private BigDecimal price;

    @NotNull
    private ProductStatus status;

    @NotNull
    private Long typeId;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long depotId;

    @NotNull
    private Boolean available;

    @NotNull
    private Long stockQuantity;
}
