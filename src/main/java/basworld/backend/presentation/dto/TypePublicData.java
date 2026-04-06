package basworld.backend.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TypePublicData {
    private Long id;
    private String name;
    private TypePublicData parent;
}
