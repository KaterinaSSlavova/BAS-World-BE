package basworld.backend.business.impl.depot;

import basworld.backend.business.useCase.depot.ArchiveDepotUseCase;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchiveDepotUseCaseImpl implements ArchiveDepotUseCase {
    private final DepotRepository depotRepository;

    @Override
    public void archiveDepot(Long id) {
        Depot depot = depotRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Depot not found"));
        depot.archiveDepot();
        depotRepository.saveDepot(depot);
    }
}
