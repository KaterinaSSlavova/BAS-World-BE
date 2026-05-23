package basworld.backend.presentation.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockAlertsResponse {
    private Long productId;
    private String productName;
    private String category;
    private String depotName;
    private Long stockQuantity;
    private Integer stockThreshold;
    private String status;
}
