package basworld.backend.business.impl.category;

import basworld.backend.business.useCase.category.GetCategoryUseCase;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategoryUseCaseImpl implements GetCategoryUseCase {
    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category does not exists!"));
    }
}
