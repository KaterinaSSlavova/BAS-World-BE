package basworld.backend.business.impl;

import basworld.backend.business.useCase.CreateDepotUseCase;
import basworld.backend.presentation.dto.CreateDepotRequest;
import basworld.backend.presentation.dto.DepotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDepotUseCaseImpl implements CreateDepotUseCase {

    @Override
    public DepotResponse createDepot(CreateDepotRequest createDepotRequest) {
        return null;
    }
}
