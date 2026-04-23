package basworld.backend.domain.brand;

import lombok.Getter;

@Getter
public class Brand {
    Long id;
    String name;
    public Brand(Long id, String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.id = id;
        this.name = name;
    }
    public Brand(String brandName) {
        if (brandName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = brandName;
    }
}
