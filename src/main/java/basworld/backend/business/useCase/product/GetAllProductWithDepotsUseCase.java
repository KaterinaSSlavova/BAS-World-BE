package basworld.backend.business.useCase.product;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;

import java.util.List;
import java.util.Map;

public interface GetAllProductWithDepotsUseCase {
    Map<Product, List<ProductDepot>>  getAll();
}
