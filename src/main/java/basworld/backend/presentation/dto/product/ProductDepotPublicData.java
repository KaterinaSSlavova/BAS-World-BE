package basworld.backend.presentation.dto.product;

import basworld.backend.presentation.dto.depot.DepotResponse;
import basworld.backend.presentation.dto.supplier.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder @Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDepotPublicData {
    private DepotResponse depot;
    private boolean isAvailable;
    private Long stockQuantity;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private Integer stockThreshold;
    private SupplierResponse supplier;

}
