package basworld.backend.business.impl.type;

import basworld.backend.business.useCase.type.GetAllTypesUseCase;
import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTypesUseCaseImpl implements GetAllTypesUseCase {
    private final TypeRepository typeRepository;

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }
}