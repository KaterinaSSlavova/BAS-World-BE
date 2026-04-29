package basworld.backend.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor
@Builder @NoArgsConstructor
public class ProductDepotRequest {
    private Long depotId;
    private Long stockQuantity;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private boolean available;
}
