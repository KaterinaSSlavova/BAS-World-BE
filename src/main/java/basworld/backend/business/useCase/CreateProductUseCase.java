package basworld.backend.business.useCase;

import basworld.backend.presentation.dto.CreateProductRequest;
import basworld.backend.presentation.dto.ProductPublicData;

public interface CreateProductUseCase {
    ProductPublicData createProduct(CreateProductRequest request);
}
