package basworld.backend.business.impl;

import basworld.backend.business.useCase.GetAllProductDepotUseCase;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.repository.ProductDepotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductDepotUseCaseImpl implements GetAllProductDepotUseCase {

    private final ProductDepotRepository productDepotRepository;

    public GetAllProductDepotUseCaseImpl(ProductDepotRepository productDepotRepository) {
        this.productDepotRepository = productDepotRepository;
    }

    public List<ProductDepot> execute() {
        return productDepotRepository.findAll();
    }
}