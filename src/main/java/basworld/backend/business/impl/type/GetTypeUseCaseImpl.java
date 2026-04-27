package basworld.backend.business.impl.type;

import basworld.backend.business.useCase.type.GetTypeUseCase;
import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTypeUseCaseImpl implements GetTypeUseCase {
    private final TypeRepository typeRepository;

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type does not exists!"));
    }
}
