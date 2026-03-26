package basworld.backend.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
public class Type {
    private Long id;
    private String name;
    private Type parent;
    private List<Type> subTypes;
    // Helper method to check if it's a root type (e.g., Service, Physical)
    public boolean isRoot() {
        return parent == null;
    }
    public Type(String name, Type parent) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
        this.parent = parent;
    }
    public Type(Long id, String name, Type parent) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
}
