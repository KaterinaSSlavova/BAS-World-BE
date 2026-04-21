package basworld.backend.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryPublicData {
    private Long id;
    private String name;
    private CategoryPublicData parent;
}
