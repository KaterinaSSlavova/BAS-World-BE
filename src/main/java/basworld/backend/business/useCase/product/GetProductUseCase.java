package basworld.backend.business.useCase.product;

import basworld.backend.domain.product.Product;

public interface GetProductUseCase {
    Product getProductById(long id);
}
