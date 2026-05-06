package basworld.backend.type;

import basworld.backend.business.impl.type.UpdateTypeUseCaseImpl;
import basworld.backend.domain.repository.TypeRepository;
import basworld.backend.domain.type.Type;
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
class UpdateTypeUseCaseImplTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private UpdateTypeUseCaseImpl updateTypeUseCase;

    private Type existing;
    private Type parent;

    @BeforeEach
    void setUp() {
        parent = Type.builder()
                .id(1L)
                .name("Large")
                .isArchived(false)
                .parent(null)
                .build();

        existing = Type.builder()
                .id(2L)
                .name("Small")
                .isArchived(false)
                .parent(parent)
                .build();
    }

    @Test
    void updateType_ShouldUpdateName_WhenNameIsUnique() {
        // Arrange
        Type updated = Type.builder()
                .id(2L)
                .name("Medium")
                .isArchived(false)
                .parent(parent)
                .build();
        when(typeRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(typeRepository.existsByNameAndIsArchivedFalse("Medium")).thenReturn(false);
        when(typeRepository.save(any(Type.class))).thenReturn(updated);

        Type request = Type.builder()
                .name("Medium")
                .isArchived(false)
                .parent(null)
                .build();

        // Act
        Type result = updateTypeUseCase.updateType(2L, request, null);

        // Assert
        assertNotNull(result);
        assertEquals("Medium", result.getName());
        assertEquals(parent, result.getParent());
        verify(typeRepository, times(1)).findById(2L);
        verify(typeRepository, times(1)).existsByNameAndIsArchivedFalse("Medium");
        verify(typeRepository, times(1)).save(existing);
    }

    @Test
    void updateType_ShouldKeepExistingParent_WhenParentIdIsNull() {
        // Arrange
        when(typeRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(typeRepository.save(any(Type.class))).thenReturn(existing);

        Type request = Type.builder()
                .name("Small") // same name — existsByName never called
                .isArchived(false)
                .parent(null)
                .build();

        // Act
        Type result = updateTypeUseCase.updateType(2L, request, null);

        // Assert
        assertNotNull(result);
        assertEquals(parent, result.getParent());
        verify(typeRepository, never()).existsByNameAndIsArchivedFalse(any());
        verify(typeRepository, times(1)).save(existing);
    }

    @Test
    void updateType_ShouldUpdateParent_WhenParentIdIsProvided() {
        // Arrange
        Type newParent = Type.builder()
                .id(3L)
                .name("Extra Large")
                .isArchived(false)
                .parent(null)
                .build();
        Type updated = Type.builder()
                .id(2L)
                .name("Small")
                .isArchived(false)
                .parent(newParent)
                .build();
        when(typeRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(typeRepository.findById(3L)).thenReturn(Optional.of(newParent));
        when(typeRepository.save(any(Type.class))).thenReturn(updated);

        Type request = Type.builder()
                .name("Small") // same name — existsByName never called
                .isArchived(false)
                .parent(null)
                .build();

        // Act
        Type result = updateTypeUseCase.updateType(2L, request, 3L);

        // Assert
        assertNotNull(result);
        assertEquals("Extra Large", result.getParent().getName());
        verify(typeRepository, never()).existsByNameAndIsArchivedFalse(any());
        verify(typeRepository, times(1)).findById(3L);
        verify(typeRepository, times(1)).save(existing);
    }

    @Test
    void updateType_ShouldNotCheckDuplicateName_WhenNameIsUnchanged() {
        // Arrange
        when(typeRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(typeRepository.save(any(Type.class))).thenReturn(existing);

        Type request = Type.builder()
                .name("Small") // same name
                .isArchived(false)
                .parent(null)
                .build();

        // Act
        Type result = updateTypeUseCase.updateType(2L, request, null);

        // Assert
        assertNotNull(result);
        verify(typeRepository, never()).existsByNameAndIsArchivedFalse(any());
        verify(typeRepository, times(1)).save(existing);
    }

    @Test
    void updateType_ShouldThrowException_WhenTypeNotFound() {
        // Arrange
        when(typeRepository.findById(999L)).thenReturn(Optional.empty());

        Type request = Type.builder()
                .name("Small")
                .isArchived(false)
                .parent(null)
                .build();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateTypeUseCase.updateType(999L, request, null)
        );

        assertEquals("Type not found with id: 999", exception.getMessage());
        verify(typeRepository, never()).save(any());
    }

    @Test
    void updateType_ShouldThrowException_WhenNameAlreadyExists() {
        // Arrange
        when(typeRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(typeRepository.existsByNameAndIsArchivedFalse("Extra Large")).thenReturn(true);

        Type request = Type.builder()
                .name("Extra Large")
                .isArchived(false)
                .parent(null)
                .build();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateTypeUseCase.updateType(2L, request, null)
        );

        assertEquals("Type with name 'Extra Large' already exists", exception.getMessage());
        verify(typeRepository, never()).save(any());
    }

    @Test
    void updateType_ShouldThrowException_WhenParentNotFound() {
        // Arrange
        when(typeRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(typeRepository.findById(999L)).thenReturn(Optional.empty());

        Type request = Type.builder()
                .name("Small") // same name — existsByName never called
                .isArchived(false)
                .parent(null)
                .build();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateTypeUseCase.updateType(2L, request, 999L)
        );

        assertEquals("Parent type not found", exception.getMessage());
        verify(typeRepository, never()).save(any());
    }
}