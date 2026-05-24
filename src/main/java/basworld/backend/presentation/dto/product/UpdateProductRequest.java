package basworld.backend.presentation.dto.product;

import basworld.backend.domain.product.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProductRequest {

    @NotBlank
    private String name;

    private String description;

    private Long brandId;

    @NotNull
    private ProductStatus status;

    @NotNull
    private Long typeId;

    @NotNull
    private Long categoryId;

    @NotNull
    Long vehicleTypeId;

    @NotNull
    private Long supplierId;

    List<ProductDepotRequest> productDepots;
}
