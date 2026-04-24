package basworld.backend.business.impl.depot;

import basworld.backend.business.useCase.depot.UpdateDepotUseCase;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateDepotUseCaseImpl implements UpdateDepotUseCase {
    private final DepotRepository depotRepository;

    @Override
    public Depot updateDepot(Depot newDepot) {
        Depot oldDepot = depotRepository.findById(newDepot.getId())
                .orElseThrow(() -> new IllegalArgumentException("Depot not found"));

        if(depotRepository.existsByNameAndArchivedFalse(newDepot.getDepotName())
                && !oldDepot.getDepotName().equalsIgnoreCase(newDepot.getDepotName())) {
            throw new IllegalArgumentException("Depot already exists!");
        }
        oldDepot.setDepotName(newDepot.getDepotName());
        oldDepot.setLocation(newDepot.getLocation());
        return depotRepository.saveDepot(oldDepot);
    }
}
