package basworld.backend.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DepotOverviewDTO {
    private Long id;
    private String depotName;
    private String location;
    private Long numberOfProducts;
}
