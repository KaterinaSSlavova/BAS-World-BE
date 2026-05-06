package basworld.backend.type;

import basworld.backend.business.impl.type.CreateTypeUseCaseImpl;
import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;
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
class CreateTypeUseCaseImplTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private CreateTypeUseCaseImpl createTypeUseCase;

    private Type type;
    private Type parent;

    @BeforeEach
    void setUp() {
        parent = Type.builder()
                .id(1L)
                .name("Large")
                .isArchived(false)
                .parent(null)
                .build();

        type = Type.builder()
                .id(2L)
                .name("Small")
                .isArchived(false)
                .parent(null)
                .build();
    }

    @Test
    void createType_ShouldSaveType_WhenNoParentAndNameIsUnique() {
        // Arrange
        when(typeRepository.existsByNameAndIsArchivedFalse("Small")).thenReturn(false);
        when(typeRepository.save(any(Type.class))).thenReturn(type);

        // Act
        Type result = createTypeUseCase.createType(type, null);

        // Assert
        assertNotNull(result);
        assertEquals("Small", result.getName());
        assertNull(result.getParent());
        assertFalse(result.isArchived());
        verify(typeRepository, times(1)).existsByNameAndIsArchivedFalse("Small");
        verify(typeRepository, never()).findById(any());
        verify(typeRepository, times(1)).save(any(Type.class));
    }

    @Test
    void createType_ShouldSaveTypeWithParent_WhenParentExists() {
        // Arrange
        Type saved = Type.builder()
                .id(2L)
                .name("Small")
                .isArchived(false)
                .parent(parent)
                .build();
        when(typeRepository.existsByNameAndIsArchivedFalse("Small")).thenReturn(false);
        when(typeRepository.findById(1L)).thenReturn(Optional.of(parent));
        when(typeRepository.save(any(Type.class))).thenReturn(saved);

        // Act
        Type result = createTypeUseCase.createType(type, 1L);

        // Assert
        assertNotNull(result);
        assertEquals("Small", result.getName());
        assertNotNull(result.getParent());
        assertEquals("Large", result.getParent().getName());
        verify(typeRepository, times(1)).existsByNameAndIsArchivedFalse("Small");
        verify(typeRepository, times(1)).findById(1L);
        verify(typeRepository, times(1)).save(any(Type.class));
    }

    @Test
    void createType_ShouldThrowException_WhenNameAlreadyExists() {
        // Arrange
        when(typeRepository.existsByNameAndIsArchivedFalse("Small")).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createTypeUseCase.createType(type, null)
        );

        assertEquals("Type with name 'Small' already exists", exception.getMessage());
        verify(typeRepository, times(1)).existsByNameAndIsArchivedFalse("Small");
        verify(typeRepository, never()).findById(any());
        verify(typeRepository, never()).save(any());
    }

    @Test
    void createType_ShouldThrowException_WhenParentNotFound() {
        // Arrange
        when(typeRepository.existsByNameAndIsArchivedFalse("Small")).thenReturn(false);
        when(typeRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> createTypeUseCase.createType(type, 999L)
        );

        assertEquals("Parent not found", exception.getMessage());
        verify(typeRepository, times(1)).existsByNameAndIsArchivedFalse("Small");
        verify(typeRepository, times(1)).findById(999L);
        verify(typeRepository, never()).save(any());
    }
}