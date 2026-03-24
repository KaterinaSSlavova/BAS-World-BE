package basworld.backend.domain.product;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.type.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter @AllArgsConstructor
public class Product {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private ProductStatus status;
    private Type type;
    private Category category;
}
