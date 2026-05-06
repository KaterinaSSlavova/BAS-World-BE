package basworld.backend.business.useCase.product;

import basworld.backend.business.command.UpdateProductCommand;
import basworld.backend.business.result.ProductWithDepotsResult;

public interface UpdateProductUseCase {
    ProductWithDepotsResult updateProduct(Long productId, UpdateProductCommand command);
}
