package basworld.backend.business.useCase;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.presentation.dto.ProductDepotResponse;

public interface UpdateProductUseCase {
    ProductDepot updateProduct(ProductDepot productDepot);
}
