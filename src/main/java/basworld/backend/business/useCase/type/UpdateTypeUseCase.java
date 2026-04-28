package basworld.backend.business.useCase.type;

import basworld.backend.domain.type.Type;

public interface UpdateTypeUseCase {
    Type updateType(Long id, Type type, Long parentId);
}
