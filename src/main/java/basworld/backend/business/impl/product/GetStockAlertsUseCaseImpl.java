package basworld.backend.business.impl.product;

import basworld.backend.business.useCase.product.GetStockAlertsUseCase;
import basworld.backend.domain.depot.StockAlert;
import basworld.backend.domain.depot.StockAlertStatus;
import basworld.backend.domain.repository.ProductDepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetStockAlertsUseCaseImpl implements GetStockAlertsUseCase {
    private final ProductDepotRepository productDepotRepository;

    @Override
    public List<StockAlert> getStockAlerts() {
        return productDepotRepository.findAllWithLowStock()
                .stream().map(pd -> new StockAlert(
                        pd,
                        pd.getStockQuantity() == 0 ? StockAlertStatus.OUT_OF_STOCK : StockAlertStatus.LOW_STOCK
                )).toList();
    }
}