package basworld.backend.business.impl;

import basworld.backend.business.useCase.SearchProductUseCase;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.repository.ProductDepotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductUseCaseImpl implements SearchProductUseCase {
    private final ProductDepotRepository repository;

    public SearchProductUseCaseImpl(ProductDepotRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductDepot> execute(String query) {
        return repository.search(query);
    }
}
