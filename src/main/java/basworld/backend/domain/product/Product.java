package basworld.backend.domain.product;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.type.Type;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
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


    public Product(String sku,
                   String name,
                   String description,
                   String brand,
                   BigDecimal price,
                   ProductStatus status,
                   Type type,
                   Category category) {

        validate(sku, name, description, brand, price, status, type, category);

        this.sku = sku;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.status = status;
        this.type = type;
        this.category = category;
    }

    @Builder
    public Product(Long id,
                   String sku,
                   String name,
                   String description,
                   String brand,
                   BigDecimal price,
                   ProductStatus status,
                   Type type,
                   Category category) {

        validate(sku, name, description, brand, price, status, type, category);

        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.status = status;
        this.type = type;
        this.category = category;
    }

    private void validate(String sku,
                          String name,
                          String description,
                          String brand,
                          BigDecimal price,
                          ProductStatus status,
                          Type type,
                          Category category) {

        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU must not be null or blank");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description must not be null or blank");
        }

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand must not be null or blank");
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("Type must not be null");
        }

        if (category == null) {
            throw new IllegalArgumentException("Category must not be null");
        }
    }

    public void setId(Long id) {
        this.id = id;
    }
}
