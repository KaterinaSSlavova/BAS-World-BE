package basworld.backend.business.useCase.type;

import basworld.backend.domain.type.Type;

public interface CreateTypeUseCase {
    Type createType(Type type, Long parentId);
}
