package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.GetProductInsightsUseCase;
import basworld.backend.domain.product.ProductInsights;
import basworld.backend.presentation.dto.ProductInsightsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-insights")
public class ProductInsightsController {

    private final GetProductInsightsUseCase getProductInsightsUseCase;

    public ProductInsightsController(GetProductInsightsUseCase getProductInsightsUseCase) {
        this.getProductInsightsUseCase = getProductInsightsUseCase;
    }

    @GetMapping("/depot/{depotId}")
    public ProductInsightsResponse getInsightsByDepot(@PathVariable Long depotId) {
        ProductInsights insights = getProductInsightsUseCase.executeByDepot(depotId);

        return new ProductInsightsResponse(
                insights.totalProducts(),
                insights.lowStockProducts(),
                insights.unavailableItems(),
                insights.inventoryValue()
        );
    }

    @GetMapping("/overall")
    public ProductInsightsResponse getInsightsOverall() {
        ProductInsights insights = getProductInsightsUseCase.executeOverall();

        return new ProductInsightsResponse(
                insights.totalProducts(),
                insights.lowStockProducts(),
                insights.unavailableItems(),
                insights.inventoryValue()
        );
    }
}