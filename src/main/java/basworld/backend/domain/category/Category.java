package basworld.backend.domain.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {

    private Long id;
    private String name;
    private boolean isArchived;
    private Category parent;

    public Category(String name, Category parent) {
        validate(name);
        this.name = name;
        this.parent = parent;
        this.isArchived = false;
    }

    @Builder
    public Category(Long id, String name, boolean isArchived, Category parent) {
        validate(name);
        this.id = id;
        this.name = name;
        this.isArchived = isArchived;
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

    public void archive() {
        this.isArchived = true;
    }


}