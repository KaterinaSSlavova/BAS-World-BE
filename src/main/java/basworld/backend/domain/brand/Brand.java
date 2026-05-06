package basworld.backend.domain.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Brand {
    Long id;
    String name;
    String picture;
    boolean archived;

    public Brand(Long id, String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.id = id;
        this.name = name;
    }

    public Brand(Long id, String name, String picture) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public Brand(Long id, String name, String picture, boolean archived) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.archived = archived;
    }

    public Brand(String brandName) {
        if (brandName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = brandName;
    }
}
