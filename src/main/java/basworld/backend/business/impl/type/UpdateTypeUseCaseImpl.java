package basworld.backend.business.impl.type;

import basworld.backend.business.useCase.type.UpdateTypeUseCase;
import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTypeUseCaseImpl implements UpdateTypeUseCase {
    private final TypeRepository typeRepository;

    @Override
    public Type updateType(Long id, Type type, Long parentId) {
        Type existing = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found with id: " + id));

        if (!existing.getName().equalsIgnoreCase(type.getName()) &&
                typeRepository.existsByNameAndIsArchivedFalse(type.getName())) {
            throw new IllegalArgumentException("Type with name '" + type.getName() + "' already exists");
        }

        Type parent = existing.getParent();
        if (parentId != null) {
            parent = typeRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Parent type not found"));
        }

        existing.setName(type.getName());
        existing.setParent(parent);
        return typeRepository.save(existing);
    }
}