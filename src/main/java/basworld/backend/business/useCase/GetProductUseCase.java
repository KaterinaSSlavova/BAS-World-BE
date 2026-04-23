package basworld.backend.business.useCase;

import basworld.backend.domain.product.Product;

public interface GetProductUseCase {
    Product getProductById(long id);
}
