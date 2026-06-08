package basworld.backend.presentation.dto.analytics;

import basworld.backend.presentation.dto.product.ProductPublicData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @AllArgsConstructor @Setter
public class AnalyticsDTO{
    private List<CategoryValueDTO> stockValueByCategory;
    private List<DepotInventoryValueDTO> productCountByDepot;
    private List<DepotProductCountDTO> inventoryValueByDepot;
    private ProductPublicData highestQuantityProduct;
}
