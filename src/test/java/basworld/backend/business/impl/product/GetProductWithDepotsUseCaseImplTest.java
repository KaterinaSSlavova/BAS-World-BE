package basworld.backend.business.impl.product;

import basworld.backend.business.exception.ProductNotFound;
import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductDepotRepository;
import basworld.backend.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class GetProductWithDepotsUseCaseImplTest {

    @Mock private ProductRepository productRepository;
    @Mock private ProductDepotRepository productDepotRepository;

    @InjectMocks
    private GetProductWithDepotsUseCaseImpl useCase;

    @Test
    void getProductWithDepots_shouldReturnProductAndDepots() {
        // Arrange
        Long productId = 1L;

        Product product = mock(Product.class);
        when(product.getId()).thenReturn(productId);

        List<ProductDepot> depots = List.of(
                mock(ProductDepot.class),
                mock(ProductDepot.class)
        );

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(productDepotRepository.findByProductId(productId))
                .thenReturn(depots);

        // Act
        ProductWithDepotsResult result =
                useCase.getProductWithDepots(productId);

        // Assert
        assertNotNull(result);
        assertEquals(product, result.product());
        assertEquals(depots, result.depots());

        verify(productRepository).findById(productId);
        verify(productDepotRepository).findByProductId(productId);
    }

    @Test
    void getProductWithDepots_shouldThrow_whenProductNotFound() {
        // Arrange
        Long productId = 1L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ProductNotFound.class,
                () -> useCase.getProductWithDepots(productId));

        verify(productRepository).findById(productId);
        verifyNoInteractions(productDepotRepository);
    }
    @Test
    void shouldReturnEmptyDepotList_whenNoDepots() {
        Long productId = 1L;

        Product product = mock(Product.class);
        when(product.getId()).thenReturn(productId);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(productDepotRepository.findByProductId(productId))
                .thenReturn(List.of());

        ProductWithDepotsResult result =
                useCase.getProductWithDepots(productId);

        assertTrue(result.depots().isEmpty());
    }
}