package basworld.backend.business.impl.product;

import basworld.backend.business.useCase.product.GetProductUseCase;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductUseCaseImpl implements GetProductUseCase {
    private final ProductRepository productRepository;

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product not found!"));
    }
}
