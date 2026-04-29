package basworld.backend.business.useCase.product;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.domain.depot.ProductDepot;
import java.util.List;

public interface CreateProductUseCase {
    List<ProductDepot> createProduct(CreateProductCommand request);
}
