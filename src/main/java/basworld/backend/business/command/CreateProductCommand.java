package basworld.backend.business.command;

import basworld.backend.domain.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class CreateProductCommand {
    private String sku;
    private String name;
    private String description;
    private Long brandId;
    private ProductStatus status;
    private Long typeId;
    private Long categoryId;

    //depot
    private List<ProductDepotCommand> productDepots;
}
