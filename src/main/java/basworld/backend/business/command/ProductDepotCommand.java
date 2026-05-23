package basworld.backend.business.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class ProductDepotCommand {
    private Long depotId;
    private Long stockQuantity;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private boolean available;
    private Integer stockThreshold;
}
