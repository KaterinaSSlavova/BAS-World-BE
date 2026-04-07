package basworld.backend.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDepotPublicData {
    private ProductPublicData product;
    private DepotResponse depot;
    private boolean isAvailable;
    private Long stockQuantity;
}
