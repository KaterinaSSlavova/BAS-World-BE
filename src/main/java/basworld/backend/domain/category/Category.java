package basworld.backend.domain.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Category {
    private Long id;
    private String name;
    private Category parent;

    public Category(String name, Category parent) {
        validate(name);
        this.name = name;
        this.parent = parent;
    }

    @Builder
    public Category(Long id, String name, Category parent) {
        validate(name);
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

    public boolean isRoot() {
        return parent == null;
    }
}

