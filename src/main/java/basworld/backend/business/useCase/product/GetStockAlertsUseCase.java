package basworld.backend.business.useCase.product;

import basworld.backend.domain.depot.StockAlert;

import java.util.List;

public interface GetStockAlertsUseCase {
    List<StockAlert> getStockAlerts();
}