package basworld.backend.business.useCase.product;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.domain.depot.ProductDepot;

public interface CreateProductUseCase {
    ProductDepot createProduct(CreateProductCommand request);
}
