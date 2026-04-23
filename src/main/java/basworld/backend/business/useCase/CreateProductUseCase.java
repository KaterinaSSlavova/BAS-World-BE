package basworld.backend.business.useCase;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.presentation.dto.CreateProductRequest;

public interface CreateProductUseCase {
    ProductDepot createProduct(CreateProductRequest request);
}
