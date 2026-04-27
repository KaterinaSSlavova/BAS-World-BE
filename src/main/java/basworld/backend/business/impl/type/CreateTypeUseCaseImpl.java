package basworld.backend.business.impl.type;

import basworld.backend.business.useCase.type.CreateTypeUseCase;
import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTypeUseCaseImpl implements CreateTypeUseCase {
    private final TypeRepository typeRepository;

    @Override
    public Type createType(Type type, Long parentId) {
        if (typeRepository.existsByNameAndIsArchivedFalse(type.getName())) {
            throw new IllegalArgumentException("Type with name '" + type.getName() + "' already exists");
        }

        Type parent = null;
        if (parentId != null) {
            parent = typeRepository.findById(parentId)
                    .orElseThrow(() -> new EntityNotFoundException("Parent not found"));
        }

        return typeRepository.save(Type.builder()
                .name(type.getName())
                .isArchived(false)
                .parent(parent)
                .build());
    }
}