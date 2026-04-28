package basworld.backend.business.useCase.depot;

import basworld.backend.domain.depot.Depot;

public interface GetDepotUseCase {
    Depot getDepotById(long id);
}
