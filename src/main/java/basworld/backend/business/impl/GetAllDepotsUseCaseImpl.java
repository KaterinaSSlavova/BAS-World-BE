package basworld.backend.business.impl;

import basworld.backend.business.useCase.GetAllDepotsUseCase;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import basworld.backend.domain.repository.ProductInsightsRepository;
import basworld.backend.presentation.dto.DepotOverviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllDepotsUseCaseImpl implements GetAllDepotsUseCase {
    private final ProductInsightsRepository productInsightsRepository;
    private final DepotRepository depotRepository;

    @Override
    public List<DepotOverviewDTO> getDepotOverview() {
        List<Depot> allDepots = depotRepository.findAll();
        List<DepotOverviewDTO> depotsDTO = new ArrayList<>();

        for(Depot depot:allDepots){
            Long productCount = productInsightsRepository.countProductsByDepotId(depot.getId());
            depotsDTO.add(new DepotOverviewDTO
                    (depot.getDepotName(), depot.getLocation(),  productCount));
        }

        return depotsDTO;
    }
}
