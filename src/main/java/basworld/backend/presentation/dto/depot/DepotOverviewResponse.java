package basworld.backend.presentation.dto.depot;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DepotOverviewResponse {
    List<DepotOverviewDTO> depots;
}