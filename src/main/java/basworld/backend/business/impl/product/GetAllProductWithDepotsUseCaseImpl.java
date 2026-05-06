package basworld.backend.business.impl.product;

import basworld.backend.business.useCase.product.GetAllProductWithDepotsUseCase;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductDepotRepository;
import basworld.backend.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllProductWithDepotsUseCaseImpl implements GetAllProductWithDepotsUseCase {
    private final ProductRepository productRepository;
    private final ProductDepotRepository productDepotRepository;
    @Override
    public Map<Product, List<ProductDepot>> getAll(){
        List<Product> products = productRepository.findAll();
        Set<Long> productIds = products.stream()
                .map(Product::getId)
                .collect(Collectors.toSet());
        List<ProductDepot> depots = productDepotRepository.findByProductIn(productIds.stream().toList());

        Map<Long, List<ProductDepot>> depotMap =
                depots.stream()
                        .collect(Collectors.groupingBy(pd -> pd.getProduct().getId()));
        return products.stream()
                .collect(Collectors.toMap(
                        product -> product,
                        product -> depotMap.getOrDefault(product.getId(), List.of())
                ));
    }

}
