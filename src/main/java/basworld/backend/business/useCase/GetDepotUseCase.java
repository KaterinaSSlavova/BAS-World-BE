package basworld.backend.business.useCase;

import basworld.backend.domain.depot.Depot;

public interface GetDepotUseCase {
    Depot getDepotById(long id);
}
