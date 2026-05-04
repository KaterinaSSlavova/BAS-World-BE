package basworld.backend.business.useCase.product;

import basworld.backend.business.command.UpdateProductCommand;
import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.domain.depot.ProductDepot;

import java.util.List;

public interface UpdateProductUseCase {
    ProductWithDepotsResult updateProduct(Long productId, UpdateProductCommand command);
}
