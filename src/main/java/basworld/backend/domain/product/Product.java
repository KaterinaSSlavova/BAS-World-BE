package basworld.backend.domain.product;

import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.type.Type;
import basworld.backend.domain.vehicleType.VehicleType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private Brand brand;
    private ProductStatus status;
    private Type type;
    private Category category;
    private VehicleType vehicleType;


    public Product(String sku,
                   String name,
                   String description,
                   Brand brand,
                   ProductStatus status,
                   Type type,
                   Category category,
                   VehicleType vehicleType ) {

        validate(sku, name, description, brand, status, type, category, vehicleType);

        this.sku = sku;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.status = status;
        this.type = type;
        this.category = category;
        this.vehicleType = vehicleType;
    }

    @Builder
    public Product(Long id,
                   String sku,
                   String name,
                   String description,
                   Brand brand,
                   ProductStatus status,
                   Type type,
                   Category category,
                   VehicleType vehicleType) {

        validate(sku, name, description, brand, status, type, category, vehicleType);

        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.status = status;
        this.type = type;
        this.category = category;
        this.vehicleType = vehicleType;
    }

    private void validate(String sku,
                          String name,
                          String description,
                          Brand brand,
                          ProductStatus status,
                          Type type,
                          Category category,
                          VehicleType vehicleType ) {

        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU must not be null or blank");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description must not be null or blank");
        }

        if (brand == null) {
            throw new IllegalArgumentException("Brand must not be null or blank");
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

        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type must not be null");
        }
    }

    public void update(String name,
                       String description,
                       Brand brand,
                       ProductStatus status,
                       Type type,
                       Category category,
                       VehicleType vehicleType) {

        validate(this.sku, name, description, brand, status, type, category, vehicleType);

        this.name = name;
        this.description = description;
        this.brand = brand;
        this.status = status;
        this.type = type;
        this.category = category;
        this.vehicleType = vehicleType;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
