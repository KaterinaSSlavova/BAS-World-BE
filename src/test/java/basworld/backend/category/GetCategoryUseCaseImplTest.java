package basworld.backend.category;

import basworld.backend.business.impl.category.GetCategoryUseCaseImpl;
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
class GetCategoryUseCaseImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private GetCategoryUseCaseImpl getCategoryUseCase;

    private Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id(1L)
                .name("Tyres")
                .isArchived(false)
                .parent(null)
                .build();
    }

    @Test
    void findById_ShouldReturnCategory_WhenCategoryExists() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // Act
        Category result = getCategoryUseCase.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Tyres", result.getName());
        assertFalse(result.isArchived());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenCategoryNotFound() {
        // Arrange
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> getCategoryUseCase.findById(999L)
        );

        assertEquals("Category does not exists!", exception.getMessage());
        verify(categoryRepository, times(1)).findById(999L);
    }
}