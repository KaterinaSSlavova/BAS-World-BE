package basworld.backend.presentation.dto.product;

import basworld.backend.domain.product.ProductStatus;
import basworld.backend.presentation.dto.CategoryPublicData;
import basworld.backend.presentation.dto.TypePublicData;
import basworld.backend.presentation.dto.brand.BrandResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ProductPublicData {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private BrandResponse brand;
    private ProductStatus status;
    private TypePublicData type;
    private CategoryPublicData category;
}
