package basworld.backend.business.impl.product;

import basworld.backend.business.useCase.product.GetAllProductUseCase;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductUseCaseImpl implements GetAllProductUseCase {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
