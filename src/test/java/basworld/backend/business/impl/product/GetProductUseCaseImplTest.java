package basworld.backend.business.impl.product;

import basworld.backend.business.exception.ProductNotFound;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class GetProductUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductUseCaseImpl useCase;

    @Test
    void getProductById_shouldReturnProduct_whenFound() {
        // Arrange
        long productId = 1L;
        Product product = mock(Product.class);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        // Act
        Product result = useCase.getProductById(productId);

        // Assert
        assertNotNull(result);
        assertEquals(product, result);

        verify(productRepository).findById(productId);
    }

    @Test
    void getProductById_shouldThrow_whenNotFound() {
        // Arrange
        long productId = 1L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ProductNotFound.class,
                () -> useCase.getProductById(productId));

        verify(productRepository).findById(productId);
    }
}