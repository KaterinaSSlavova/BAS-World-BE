package basworld.backend.business.impl.product;

import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.business.useCase.product.GetProductWithDepotsUseCase;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductDepotRepository;
import basworld.backend.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductWithDepotsUseCaseImpl implements GetProductWithDepotsUseCase {
    private final ProductRepository productRepository;
    private final ProductDepotRepository productDepotRepository;

    @Override
    public ProductWithDepotsResult getProductWithDepots(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product not found!"));
        List<ProductDepot> productDepotList = productDepotRepository.findByProductId(product.getId());
        return new  ProductWithDepotsResult(product, productDepotList);
    }
}
