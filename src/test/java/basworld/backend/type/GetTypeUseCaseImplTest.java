package basworld.backend.type;

import basworld.backend.business.impl.type.GetTypeUseCaseImpl;
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
class GetTypeUseCaseImplTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private GetTypeUseCaseImpl getTypeUseCase;

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
    void findById_ShouldReturnType_WhenTypeExists() {
        // Arrange
        when(typeRepository.findById(1L)).thenReturn(Optional.of(type));

        // Act
        Type result = getTypeUseCase.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Small", result.getName());
        assertFalse(result.isArchived());
        verify(typeRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenTypeNotFound() {
        // Arrange
        when(typeRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> getTypeUseCase.findById(999L)
        );

        assertEquals("Type does not exists!", exception.getMessage());
        verify(typeRepository, times(1)).findById(999L);
    }
}