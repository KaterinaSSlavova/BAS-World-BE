package basworld.backend.business.impl;

import basworld.backend.business.useCase.GetProductInsightsUseCase;
import basworld.backend.domain.product.ProductInsights;
import basworld.backend.domain.repository.ProductInsightsRepository;
import org.springframework.stereotype.Service;

@Service
public class GetProductInsightsUseCaseImpl implements GetProductInsightsUseCase {

    private final ProductInsightsRepository productInsightsRepository;

    public GetProductInsightsUseCaseImpl(ProductInsightsRepository productInsightsRepository) {
        this.productInsightsRepository = productInsightsRepository;
    }

    @Override
    public ProductInsights execute(Long depotId) {
        return new ProductInsights(
                productInsightsRepository.countProductsByDepotId(depotId),
                productInsightsRepository.countLowStockProductsByDepotId(depotId),
                productInsightsRepository.countUnavailableItemsByDepotId(depotId),
                productInsightsRepository.sumInventoryValueByDepotId(depotId)
        );
    }
}
