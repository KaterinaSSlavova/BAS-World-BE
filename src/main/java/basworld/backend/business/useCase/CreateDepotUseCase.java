package basworld.backend.business.useCase;

import basworld.backend.presentation.dto.CreateDepotRequest;
import basworld.backend.presentation.dto.DepotResponse;

public interface CreateDepotUseCase {
    DepotResponse createDepot(CreateDepotRequest createDepotRequest);
}
