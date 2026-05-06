package basworld.backend.business.impl.product;

import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class GetAllProductUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetAllProductUseCaseImpl useCase;

    @Test
    void getAll_shouldReturnAllProducts() {
        // Arrange
        List<Product> products = List.of(
                mock(Product.class),
                mock(Product.class)
        );

        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = useCase.getAll();

        // Assert
        assertEquals(products, result);
        verify(productRepository).findAll();
    }
}