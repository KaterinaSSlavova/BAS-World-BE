package basworld.backend.business.impl;

import basworld.backend.business.useCase.GetAnalyticsUseCase;
import basworld.backend.domain.analytics.AnalyticsData;
import basworld.backend.domain.repository.AnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetAnalyticsUseCaseImpl implements GetAnalyticsUseCase {
    private final AnalyticsRepository analyticsRepository;

    public AnalyticsData getAnalyticsData() {
        return new AnalyticsData(
                analyticsRepository.getStockValueByCategory(),
                analyticsRepository.getProductCountByDepot(),
                analyticsRepository.getInventoryValueByDepot(),
                analyticsRepository.getHighestQuantityProduct()
        );
    }
}
