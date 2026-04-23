package basworld.backend.business.impl;

import basworld.backend.business.useCase.GetDepotUseCase;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDepotUseCaseImpl implements GetDepotUseCase {
    private final DepotRepository depotRepository;

    @Override
    public Depot getDepotById(long id) {
        return depotRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Depot not found!"));
    }
}
