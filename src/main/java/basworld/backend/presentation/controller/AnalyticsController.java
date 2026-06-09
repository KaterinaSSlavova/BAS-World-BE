package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.GetAnalyticsUseCase;
import basworld.backend.domain.analytics.AnalyticsData;
import basworld.backend.presentation.dto.analytics.AnalyticsDTO;
import basworld.backend.presentation.dto.analytics.CategoryValueDTO;
import basworld.backend.presentation.dto.analytics.DepotInventoryValueDTO;
import basworld.backend.presentation.dto.analytics.DepotProductCountDTO;
import basworld.backend.presentation.dto.product.ProductPublicData;
import basworld.backend.presentation.mappers.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final GetAnalyticsUseCase getAnalyticsUseCase;
    @GetMapping
    public ResponseEntity<AnalyticsDTO> getAnalyticsData() {
        AnalyticsData data = getAnalyticsUseCase.getAnalyticsData();

        var categoryValueDTOs = data.stockValueByCategory().stream()
                .map(c -> new CategoryValueDTO(c.categoryId(), c.categoryName(), c.totalValue()))
                .collect(Collectors.toList());

        var productCountDTOs = data.productCountByDepot().stream()
                .map(d -> new DepotProductCountDTO(d.depotId(), d.depotName(), d.totalProducts()))
                .collect(Collectors.toList());

        var depotValueDTOs = data.inventoryValueByDepot().stream()
                .map(v -> new DepotInventoryValueDTO(v.depotId(), v.depotName(), v.totalValue()))
                .collect(Collectors.toList());

        ProductPublicData highestProductDto = null;
        if (data.highestQuantityProduct() != null) {
             highestProductDto = ProductDtoMapper.toProductPublicData(data.highestQuantityProduct());
        }

        AnalyticsDTO responsePayload = new AnalyticsDTO(
                categoryValueDTOs,
                depotValueDTOs,
                productCountDTOs,
                highestProductDto
        );

        return ResponseEntity.ok().body(responsePayload);
    }
}
