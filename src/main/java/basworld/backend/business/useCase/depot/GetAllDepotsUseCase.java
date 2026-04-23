package basworld.backend.business.useCase.depot;

import basworld.backend.presentation.dto.depot.DepotOverviewDTO;

import java.util.List;

public interface GetAllDepotsUseCase {
    List<DepotOverviewDTO> getDepotOverview();
}
