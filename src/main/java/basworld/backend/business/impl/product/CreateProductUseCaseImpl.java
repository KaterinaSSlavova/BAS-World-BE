package basworld.backend.business.impl.product;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.business.useCase.product.CreateProductUseCase;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
@Transactional
public class CreateProductUseCaseImpl implements CreateProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;
    private final DepotRepository depotRepository;
    private final ProductDepotRepository productDepotRepository;
    public ProductDepot createProduct(CreateProductCommand request) {
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
        var savedProduct = productRepository.save(product);
        var depot = depotRepository.findById(request.getDepotId()).orElseThrow();
        var productDepot = new ProductDepot(savedProduct, depot, request.isAvailable(), request.getStockQuantity());
        return productDepotRepository.save(productDepot);
    }

}
