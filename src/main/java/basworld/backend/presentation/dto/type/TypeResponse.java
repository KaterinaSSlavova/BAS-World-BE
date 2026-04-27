package basworld.backend.presentation.dto.type;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse {
    private Long id;
    private String name;
    private boolean isArchived;
    private Long parentId;
    private String parentName;
}