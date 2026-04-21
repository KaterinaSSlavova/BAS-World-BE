package basworld.backend.business.useCase;

import basworld.backend.presentation.dto.DepotOverviewDTO;

import java.util.List;

public interface GetAllDepotsUseCase {
    List<DepotOverviewDTO> getDepotOverview();
}
