package basworld.backend.business.command;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data @Builder
public class ProductDepotCommand {
    private Long depotId;
    private Long stockQuantity;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private boolean available;
}
