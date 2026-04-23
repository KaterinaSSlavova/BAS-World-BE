package basworld.backend.business.useCase;

import basworld.backend.domain.depot.ProductDepot;

import java.util.List;

public interface GetAllProductDepotUseCase {
    List<ProductDepot> execute();
}
