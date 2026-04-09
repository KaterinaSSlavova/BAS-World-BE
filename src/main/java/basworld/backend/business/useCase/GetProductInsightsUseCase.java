package basworld.backend.business.useCase;

import basworld.backend.domain.product.ProductInsights;

public interface GetProductInsightsUseCase {
    ProductInsights executeByDepot(Long depotId);
    ProductInsights executeOverall();
}
