package basworld.backend.business.impl.depot;

import basworld.backend.business.useCase.depot.CreateDepotUseCase;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDepotUseCaseImpl implements CreateDepotUseCase {
    private final DepotRepository depotRepository;

    @Override
    public Depot createDepot(Depot depot) {
        return depotRepository.saveDepot(depot);
    }
}
