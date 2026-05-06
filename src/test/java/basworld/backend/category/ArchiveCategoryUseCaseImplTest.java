package basworld.backend.category;

import basworld.backend.business.impl.category.ArchiveCategoryUseCaseImpl;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArchiveCategoryUseCaseImplTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    private ArchiveCategoryUseCaseImpl archiveCategoryUseCase;

    private Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id(1L)
                .name("Electronics")
                .isArchived(false)
                .parent(null)
                .build();
    }

    @Test
    void archiveCategory_ShouldDeleteCategory_WhenCategoryExists()
    {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // Act
        archiveCategoryUseCase.archiveCategory(1L);

        // Assert
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    void archiveCategory_ShouldThrowException_WhenCategoryNotFound() {
        // Arrange
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> archiveCategoryUseCase.archiveCategory(999L)
        );

        assertEquals("Category not found with id: 999", exception.getMessage());
        verify(categoryRepository, times(1)).findById(999L);
        verify(categoryRepository, never()).delete(any());
    }

}
