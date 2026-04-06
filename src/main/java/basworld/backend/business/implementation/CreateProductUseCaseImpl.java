package basworld.backend.business.implementation;

import basworld.backend.business.useCase.CreateProductUseCase;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.CategoryRepository;
import basworld.backend.domain.repository.ProductRepository;
import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.presentation.dto.CreateProductRequest;
import basworld.backend.presentation.dto.ProductPublicData;
import basworld.backend.presentation.mappers.ProductDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;
    public ProductPublicData createProduct(CreateProductRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null!");
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("CategoryId cannot be null!");
        }
        if (request.getTypeId() == null) {
            throw new IllegalArgumentException("TypeId cannot be null!");
        }
        var category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        var type = typeRepository.findById(request.getTypeId()).orElseThrow(() -> new IllegalArgumentException("Type Id not found!"));
        var product = new Product(request.getSku(), request.getName(), request.getDescription()
        , request.getBrand(), request.getPrice(), request.getStatus(), type, category);
        var result = productRepository.save(product).orElseThrow();
        return ProductDtoMapper.toProductPublicData(result);
    }

}
