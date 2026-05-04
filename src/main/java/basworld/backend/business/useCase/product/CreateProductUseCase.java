package basworld.backend.business.useCase.product;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.business.result.ProductWithDepotsResult;

public interface CreateProductUseCase {
    ProductWithDepotsResult createProduct(CreateProductCommand request);
}
