package basworld.backend.presentation.dto.type;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeRequest {
    @NotBlank
    private String name;
    private Long parentId;
}