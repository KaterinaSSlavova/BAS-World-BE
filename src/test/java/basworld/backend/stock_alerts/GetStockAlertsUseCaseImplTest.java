package basworld.backend.stock_alerts;

import basworld.backend.business.impl.product.GetStockAlertsUseCaseImpl;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.depot.StockAlert;
import basworld.backend.domain.depot.StockAlertStatus;
import basworld.backend.domain.repository.ProductDepotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetStockAlertsUseCaseImplTest {

    @Mock
    private ProductDepotRepository productDepotRepository;

    @InjectMocks
    private GetStockAlertsUseCaseImpl useCase;

    @Test
    void getStockAlerts_shouldReturnEmptyList_whenNoLowStock() {
        when(productDepotRepository.findAllWithLowStock()).thenReturn(List.of());

        List<StockAlert> result = useCase.getStockAlerts();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productDepotRepository).findAllWithLowStock();
    }

    @Test
    void getStockAlerts_shouldReturnLowStock_whenStockIsLow() {
        ProductDepot pd = mock(ProductDepot.class);
        when(pd.getStockQuantity()).thenReturn(5L);

        when(productDepotRepository.findAllWithLowStock()).thenReturn(List.of(pd));

        List<StockAlert> result = useCase.getStockAlerts();

        assertEquals(1, result.size());
        assertEquals(StockAlertStatus.LOW_STOCK, result.get(0).getStatus());
        assertEquals(pd, result.get(0).getProductDepot());
    }

    @Test
    void getStockAlerts_shouldReturnOutOfStock_whenStockIsZero() {
        ProductDepot pd = mock(ProductDepot.class);
        when(pd.getStockQuantity()).thenReturn(0L);

        when(productDepotRepository.findAllWithLowStock()).thenReturn(List.of(pd));

        List<StockAlert> result = useCase.getStockAlerts();

        assertEquals(1, result.size());
        assertEquals(StockAlertStatus.OUT_OF_STOCK, result.get(0).getStatus());
        assertEquals(pd, result.get(0).getProductDepot());
    }

    @Test
    void getStockAlerts_shouldHandleMixedStockStatuses() {
        ProductDepot outOfStock = mock(ProductDepot.class);
        when(outOfStock.getStockQuantity()).thenReturn(0L);

        ProductDepot lowStock = mock(ProductDepot.class);
        when(lowStock.getStockQuantity()).thenReturn(3L);

        when(productDepotRepository.findAllWithLowStock()).thenReturn(List.of(outOfStock, lowStock));

        List<StockAlert> result = useCase.getStockAlerts();

        assertEquals(2, result.size());
        assertEquals(StockAlertStatus.OUT_OF_STOCK, result.get(0).getStatus());
        assertEquals(StockAlertStatus.LOW_STOCK, result.get(1).getStatus());
    }
}