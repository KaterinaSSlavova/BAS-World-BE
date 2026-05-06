package basworld.backend.business.useCase.product;

import basworld.backend.business.result.ProductWithDepotsResult;

public interface GetProductWithDepotsUseCase {
    ProductWithDepotsResult getProductWithDepots(Long productId);
}
