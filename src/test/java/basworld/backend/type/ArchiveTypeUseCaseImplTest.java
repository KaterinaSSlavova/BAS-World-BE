package basworld.backend.type;

import basworld.backend.business.impl.type.ArchiveTypeUseCaseImpl;
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
class ArchiveTypeUseCaseImplTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private ArchiveTypeUseCaseImpl archiveTypeUseCase;

    private Type type;

    @BeforeEach
    void setUp() {
        type = Type.builder()
                .id(1L)
                .name("Small")
                .isArchived(false)
                .parent(null)
                .build();
    }

    @Test
    void archiveType_ShouldDeleteType_WhenTypeExists() {
        // Arrange
        when(typeRepository.findById(1L)).thenReturn(Optional.of(type));

        // Act
        archiveTypeUseCase.archiveType(1L);

        // Assert
        verify(typeRepository, times(1)).findById(1L);
        verify(typeRepository, times(1)).delete(type);
    }

    @Test
    void archiveType_ShouldThrowException_WhenTypeNotFound() {
        // Arrange
        when(typeRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> archiveTypeUseCase.archiveType(999L)
        );

        assertEquals("Type not found with id: 999", exception.getMessage());
        verify(typeRepository, times(1)).findById(999L);
        verify(typeRepository, never()).delete(any());
    }
}