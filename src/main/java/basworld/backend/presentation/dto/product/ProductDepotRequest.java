package basworld.backend.presentation.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor
@Builder @NoArgsConstructor
public class ProductDepotRequest {
    @NotNull
    private Long depotId;
    private Long stockQuantity;
    @NotNull
    private BigDecimal costPrice;
    @NotNull
    private BigDecimal salePrice;
    private boolean available;

    @NotNull
    private Integer stockThreshold;

    @NotNull
    private Long supplierId;
}
