package basworld.backend.business.command;

import basworld.backend.domain.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor @Builder
public class UpdateProductCommand {
    private String name;
    private String description;
    private Long brandId;
    private ProductStatus status;
    private Long typeId;
    private Long categoryId;
    private Long vehicleTypeId;
    private Long supplierId;

    //depot
    private List<ProductDepotCommand> productDepots;
}
