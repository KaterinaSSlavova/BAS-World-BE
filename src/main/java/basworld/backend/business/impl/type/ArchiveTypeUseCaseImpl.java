package basworld.backend.business.impl.type;

import basworld.backend.business.useCase.type.ArchiveTypeUseCase;
import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchiveTypeUseCaseImpl implements ArchiveTypeUseCase {
    private final TypeRepository typeRepository;

    @Override
    public void archiveType(Long id) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found with id: " + id));
        typeRepository.delete(type);
    }
}