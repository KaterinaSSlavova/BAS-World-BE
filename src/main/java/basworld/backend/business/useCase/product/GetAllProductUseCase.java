package basworld.backend.business.useCase.product;

import basworld.backend.domain.product.Product;

import java.util.List;

public interface GetAllProductUseCase {
    List<Product> getAll();
}
