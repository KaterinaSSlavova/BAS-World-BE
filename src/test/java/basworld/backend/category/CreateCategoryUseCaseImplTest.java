package basworld.backend.category;

import basworld.backend.business.impl.category.CreateCategoryUseCaseImpl;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
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
class CreateCategoryUseCaseImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CreateCategoryUseCaseImpl createCategoryUseCase;

    private Category category;
    private Category parent;

    @BeforeEach
    void setUp() {
        parent = Category.builder()
                .id(1L)
                .name("Tyre")
                .isArchived(false)
                .parent(null)
                .build();

        category = Category.builder()
                .id(2L)
                .name("Tyres")
                .isArchived(false)
                .parent(null)
                .build();
    }

    @Test
    void createCategory_ShouldSaveCategory_WhenNoParentAndNameIsUnique() {
        // Arrange
        when(categoryRepository.existsByNameAndIsArchivedFalse("Tyres")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Act
        Category result = createCategoryUseCase.createCategory(category, null);

        // Assert
        assertNotNull(result);
        assertEquals("Tyres", result.getName());
        assertNull(result.getParent());
        assertFalse(result.isArchived());
        verify(categoryRepository, times(1)).existsByNameAndIsArchivedFalse("Tyres");
        verify(categoryRepository, never()).findById(any());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_ShouldSaveCategoryWithParent_WhenParentExists() {
        // Arrange
        Category saved = Category.builder()
                .id(2L)
                .name("Tyres")
                .isArchived(false)
                .parent(parent)
                .build();
        when(categoryRepository.existsByNameAndIsArchivedFalse("Tyres")).thenReturn(false);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(parent));
        when(categoryRepository.save(any(Category.class))).thenReturn(saved);

        // Act
        Category result = createCategoryUseCase.createCategory(category, 1L);

        // Assert
        assertNotNull(result);
        assertEquals("Tyres", result.getName());
        assertNotNull(result.getParent());
        assertEquals("Tyre", result.getParent().getName());
        verify(categoryRepository, times(1)).existsByNameAndIsArchivedFalse("Tyres");
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_ShouldThrowException_WhenNameAlreadyExists() {
        // Arrange
        when(categoryRepository.existsByNameAndIsArchivedFalse("Tyres")).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createCategoryUseCase.createCategory(category, null)
        );

        assertEquals("Category with name 'Tyres' already exists", exception.getMessage());
        verify(categoryRepository, times(1)).existsByNameAndIsArchivedFalse("Tyres");
        verify(categoryRepository, never()).findById(any());
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void createCategory_ShouldThrowException_WhenParentNotFound() {
        // Arrange
        when(categoryRepository.existsByNameAndIsArchivedFalse("Tyres")).thenReturn(false);
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> createCategoryUseCase.createCategory(category, 999L)
        );

        assertEquals("Parent not found", exception.getMessage());
        verify(categoryRepository, times(1)).existsByNameAndIsArchivedFalse("Tyres");
        verify(categoryRepository, times(1)).findById(999L);
        verify(categoryRepository, never()).save(any());
    }
}