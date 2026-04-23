package basworld.backend.domain.type;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Type {

    private Long id;
    private String name;
    private Type parent;

    public Type(String name, Type parent) {
        validate(name);
        this.name = name;
        this.parent = parent;
    }

    @Builder
    public Type(Long id, String name, Type parent) {
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