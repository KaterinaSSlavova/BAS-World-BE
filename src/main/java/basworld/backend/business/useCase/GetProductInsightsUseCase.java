package basworld.backend.business.useCase;

import basworld.backend.domain.product.ProductInsights;

public interface GetProductInsightsUseCase {
    ProductInsights execute(Long depotId);
}
