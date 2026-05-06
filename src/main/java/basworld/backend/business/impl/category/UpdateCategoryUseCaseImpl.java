package basworld.backend.business.impl.category;

import basworld.backend.business.useCase.category.UpdateCategoryUseCase;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {
    private final CategoryRepository categoryRepository;

    @Override
    public Category updateCategory(Long id, Category category, Long parentId) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));

        if (!existing.getName().equalsIgnoreCase(category.getName()) &&
                categoryRepository.existsByNameAndIsArchivedFalse(category.getName())) {
            throw new IllegalArgumentException("Category with name '" + category.getName() + "' already exists");
        }

        Category parent = null;
        if (parentId != null) {
            parent = categoryRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
        }

        existing.setName(category.getName());
        existing.setParent(parent);
        return categoryRepository.save(existing);
    }
}