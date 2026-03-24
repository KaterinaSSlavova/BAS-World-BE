package basworld.backend.domain.category;

import basworld.backend.domain.type.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private Type parent;
    private List<Type> subTypes = new ArrayList<>();
    // Helper method to check if it's a root category
    public boolean isRoot() {
        return parent == null;
    }
}
