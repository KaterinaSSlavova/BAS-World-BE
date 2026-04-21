package basworld.backend.business.impl;

import basworld.backend.business.useCase.UpdateProductUseCase;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.repository.DepotRepository;
import basworld.backend.domain.repository.ProductDepotRepository;
import basworld.backend.domain.repository.ProductRepository;
import basworld.backend.presentation.dto.ProductDepotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {
    private final ProductDepotRepository productDepotRepository;
    private final ProductRepository productRepository;
    private final DepotRepository depotRepository;

    @Override
    public ProductDepot updateProduct(ProductDepot productDepot) {
      if(!productRepository.existsById(productDepot.getProduct().getId())){
          throw new IllegalArgumentException("Product not found!");
        }
      if(!depotRepository.existsById(productDepot.getDepot().getId())){
          throw new IllegalArgumentException("Depot not found!");
      }
      if(!productDepotRepository.existsByProductIdAndDepotId
              (productDepot.getProduct().getId(), productDepot.getDepot().getId())){
          throw new  IllegalArgumentException("This Depot does not offer this product!");
      }

      productRepository.save(productDepot.getProduct());
      return productDepotRepository.save(productDepot);
    }
}
