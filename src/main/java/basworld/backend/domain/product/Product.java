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

    public Product(String name, String description, String brand, BigDecimal price, ProductStatus status, Type type, Category category) {
        if (name == null || name.isEmpty()) throw new NullPointerException("Name is null");
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.status = status;
        this.type = type;
        this.category = category;
    }
}
