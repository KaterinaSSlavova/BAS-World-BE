package basworld.backend.domain.category;

import basworld.backend.domain.type.Type;
import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private Category parent;
    private List<Category> subCategories;
    // Helper method to check if it's a root category
    public boolean isRoot() {
        return parent == null;
    }
    public Category(String name, Category parent) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
        this.parent = parent;
    }
    public Category(Long id, String name, Category parent) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
}
