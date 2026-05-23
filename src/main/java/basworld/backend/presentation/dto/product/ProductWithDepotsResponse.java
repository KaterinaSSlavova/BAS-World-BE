package basworld.backend.presentation.dto.product;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder @Data
public class ProductWithDepotsResponse {
    ProductPublicData product;
    List<ProductDepotPublicData> depots;
}