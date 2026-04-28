package basworld.backend.business.impl.category;

import basworld.backend.business.useCase.category.CreateCategoryUseCase;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category, Long parentId) {
        if (categoryRepository.existsByNameAndIsArchivedFalse(category.getName())) {
            throw new IllegalArgumentException("Category with name '" + category.getName() + "' already exists");
        }

        Category parent = null;
        if (parentId != null) {
            parent = categoryRepository.findById(parentId)
                    .orElseThrow(() -> new EntityNotFoundException("Parent not found"));
        }

        return categoryRepository.save(Category.builder()
                .name(category.getName())
                .isArchived(false)
                .parent(parent)
                .build());
    }
}