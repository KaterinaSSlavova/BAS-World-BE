package basworld.backend.category;

import basworld.backend.business.impl.category.UpdateCategoryUseCaseImpl;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCategoryUseCaseImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private UpdateCategoryUseCaseImpl updateCategoryUseCase;

    private Category existing;
    private Category parent;

    @BeforeEach
    void setUp() {
        parent = Category.builder()
                .id(1L)
                .name("Tyre")
                .isArchived(false)
                .parent(null)
                .build();

        existing = Category.builder()
                .id(2L)
                .name("Tyres")
                .isArchived(false)
                .parent(parent)
                .build();
    }

    @Test
    void updateCategory_ShouldUpdateName_WhenNameIsUnique() {
        // Arrange
        Category updated = Category.builder()
                .id(2L)
                .name("New Tyres")
                .isArchived(false)
                .parent(null)
                .build();
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(categoryRepository.existsByNameAndIsArchivedFalse("New Tyres")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(updated);

        Category request = Category.builder()
                .name("New Tyres")
                .isArchived(false)
                .parent(null)
                .build();

        // Act
        Category result = updateCategoryUseCase.updateCategory(2L, request, null);

        // Assert
        assertNotNull(result);
        assertEquals("New Tyres", result.getName());
        verify(categoryRepository, times(1)).findById(2L);
        verify(categoryRepository, times(1)).existsByNameAndIsArchivedFalse("New Tyres");
        verify(categoryRepository, times(1)).save(existing);
    }

    @Test
    void updateCategory_ShouldClearParent_WhenParentIdIsNull() {
        // Arrange
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(categoryRepository.save(any(Category.class))).thenReturn(existing);

        Category request = Category.builder()
                .name("Tyres") // same name — existsByName never called
                .isArchived(false)
                .parent(null)
                .build();

        // Act
        Category result = updateCategoryUseCase.updateCategory(2L, request, null);

        // Assert
        assertNotNull(result);
        assertNull(result.getParent()); // parent is cleared when parentId is null
        verify(categoryRepository, never()).existsByNameAndIsArchivedFalse(any());
        verify(categoryRepository, times(1)).save(existing);
    }

    @Test
    void updateCategory_ShouldUpdateParent_WhenParentIdIsProvided() {
        // Arrange
        Category newParent = Category.builder()
                .id(3L)
                .name("Wheels")
                .isArchived(false)
                .parent(null)
                .build();
        Category updated = Category.builder()
                .id(2L)
                .name("Tyres")
                .isArchived(false)
                .parent(newParent)
                .build();
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(categoryRepository.findById(3L)).thenReturn(Optional.of(newParent));
        when(categoryRepository.save(any(Category.class))).thenReturn(updated);

        Category request = Category.builder()
                .name("Tyres")
                .isArchived(false)
                .parent(null)
                .build();

        // Act
        Category result = updateCategoryUseCase.updateCategory(2L, request, 3L);

        // Assert
        assertNotNull(result);
        assertEquals("Wheels", result.getParent().getName());
        verify(categoryRepository, never()).existsByNameAndIsArchivedFalse(any());
        verify(categoryRepository, times(1)).findById(3L);
        verify(categoryRepository, times(1)).save(existing);
    }

    @Test
    void updateCategory_ShouldNotCheckDuplicateName_WhenNameIsUnchanged() {
        // Arrange
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(categoryRepository.save(any(Category.class))).thenReturn(existing);

        Category request = Category.builder()
                .name("Tyres") // same name
                .isArchived(false)
                .parent(null)
                .build();

        // Act
        Category result = updateCategoryUseCase.updateCategory(2L, request, null);

        // Assert
        assertNotNull(result);
        verify(categoryRepository, never()).existsByNameAndIsArchivedFalse(any());
        verify(categoryRepository, times(1)).save(existing);
    }

    @Test
    void updateCategory_ShouldThrowException_WhenCategoryNotFound() {
        // Arrange
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        Category request = Category.builder()
                .name("Tyres")
                .isArchived(false)
                .parent(null)
                .build();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateCategoryUseCase.updateCategory(999L, request, null)
        );

        assertEquals("Category not found with id: 999", exception.getMessage());
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void updateCategory_ShouldThrowException_WhenNameAlreadyExists() {
        // Arrange
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(categoryRepository.existsByNameAndIsArchivedFalse("Wheels")).thenReturn(true);

        Category request = Category.builder()
                .name("Wheels")
                .isArchived(false)
                .parent(null)
                .build();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateCategoryUseCase.updateCategory(2L, request, null)
        );

        assertEquals("Category with name 'Wheels' already exists", exception.getMessage());
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void updateCategory_ShouldThrowException_WhenParentNotFound() {
        // Arrange
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        Category request = Category.builder()
                .name("Tyres")
                .isArchived(false)
                .parent(null)
                .build();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateCategoryUseCase.updateCategory(2L, request, 999L)
        );

        assertEquals("Parent category not found", exception.getMessage());
        verify(categoryRepository, never()).save(any());
    }
}