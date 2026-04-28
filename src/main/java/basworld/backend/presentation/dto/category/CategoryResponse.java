package basworld.backend.presentation.dto.category;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private boolean isArchived;
    private Long parentId;
    private String parentName;
}