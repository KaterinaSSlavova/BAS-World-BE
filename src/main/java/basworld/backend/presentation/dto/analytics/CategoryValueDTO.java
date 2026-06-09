package basworld.backend.presentation.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
public class CategoryValueDTO {
    private Long categoryId;
    private String categoryName;
    private BigDecimal totalValue;
}
