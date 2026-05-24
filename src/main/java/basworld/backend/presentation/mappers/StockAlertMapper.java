package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.StockAlert;
import basworld.backend.presentation.dto.product.StockAlertsResponse;

public class StockAlertMapper {
    public static StockAlertsResponse toResponse(StockAlert stockAlert){
        return StockAlertsResponse.builder()
                .productId(stockAlert.getProductDepot().getProduct().getId())
                .productName(stockAlert.getProductDepot().getProduct().getName())
                .category(stockAlert.getProductDepot().getProduct().getCategory().getName())
                .depotName(stockAlert.getProductDepot().getDepot().getDepotName())
                .stockQuantity(stockAlert.getProductDepot().getStockQuantity())
                .stockThreshold(stockAlert.getProductDepot().getStockThreshold())
                .status(stockAlert.getStatus().toString())
                .build();
    }
}
