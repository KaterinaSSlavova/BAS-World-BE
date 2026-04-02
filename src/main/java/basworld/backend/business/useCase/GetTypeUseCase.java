package basworld.backend.business.useCase;

import basworld.backend.domain.type.Type;

public interface GetTypeUseCase {
    Type findById(Long id);
}
