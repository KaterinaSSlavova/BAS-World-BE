package basworld.backend.business.useCase.type;

import basworld.backend.domain.type.Type;

public interface GetTypeUseCase {
    Type findById(Long id);
}
