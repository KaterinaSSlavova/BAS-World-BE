package basworld.backend.business.impl.product;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductDepotRepository;
import basworld.backend.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class GetAllProductWithDepotsUseCaseImplTest {

    @Mock private ProductRepository productRepository;
    @Mock private ProductDepotRepository productDepotRepository;

    @InjectMocks
    private GetAllProductWithDepotsUseCaseImpl useCase;

    @Test
    void getAll_shouldReturnProductsWithGroupedDepots() {

        // --- Products ---
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);

        when(product1.getId()).thenReturn(1L);
        when(product2.getId()).thenReturn(2L);

        List<Product> products = List.of(product1, product2);

        // --- Depots ---
        ProductDepot depot1 = mock(ProductDepot.class);
        ProductDepot depot2 = mock(ProductDepot.class);

        when(depot1.getProduct()).thenReturn(product1);
        when(depot2.getProduct()).thenReturn(product1);

        List<ProductDepot> depots = List.of(depot1, depot2);

        // --- Mock behavior ---
        when(productRepository.findAll()).thenReturn(products);

        when(productDepotRepository.findByProductIn(List.of(1L, 2L)))
                .thenReturn(depots);

        // --- Act ---
        Map<Product, List<ProductDepot>> result = useCase.getAll();

        // --- Assert ---
        assertEquals(2, result.size());

        // product1 has 2 depots
        assertEquals(2, result.get(product1).size());

        // product2 has no depots → empty list
        assertTrue(result.get(product2).isEmpty());

        verify(productRepository).findAll();
        verify(productDepotRepository).findByProductIn(List.of(1L, 2L));
    }
    @Test
    void getAll_shouldReturnEmptyMap_whenNoProducts() {
        when(productRepository.findAll()).thenReturn(List.of());

        Map<Product, List<ProductDepot>> result = useCase.getAll();

        assertTrue(result.isEmpty());

        verify(productDepotRepository).findByProductIn(List.of());
    }
    @Test
    void getAll_shouldReturnEmptyDepotLists_whenNoDepotsExist() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(1L);

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productDepotRepository.findByProductIn(List.of(1L)))
                .thenReturn(List.of());

        Map<Product, List<ProductDepot>> result = useCase.getAll();

        assertEquals(1, result.size());
        assertTrue(result.get(product).isEmpty());
    }
}