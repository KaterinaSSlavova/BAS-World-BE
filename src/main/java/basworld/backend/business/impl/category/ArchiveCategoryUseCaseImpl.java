package basworld.backend.business.impl.category;

import basworld.backend.business.useCase.category.ArchiveCategoryUseCase;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchiveCategoryUseCaseImpl implements ArchiveCategoryUseCase {
    private final CategoryRepository categoryRepository;

    @Override
    public void archiveCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }
}