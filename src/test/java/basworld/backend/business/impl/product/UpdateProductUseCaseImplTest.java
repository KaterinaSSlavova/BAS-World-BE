package basworld.backend.business.impl.product;

import basworld.backend.business.command.ProductDepotCommand;
import basworld.backend.business.command.UpdateProductCommand;
import basworld.backend.business.exception.ProductNotFound;
import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.product.ProductStatus;
import basworld.backend.domain.repository.*;
import basworld.backend.domain.type.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UpdateProductUseCaseImplTest {

    @Mock private ProductRepository productRepository;
    @Mock private CategoryRepository categoryRepository;
    @Mock private TypeRepository typeRepository;
    @Mock private DepotRepository depotRepository;
    @Mock private ProductDepotRepository productDepotRepository;
    @Mock private BrandRepository brandRepository;

    @InjectMocks
    private UpdateProductUseCaseImpl useCase;

    @Test
    void updateProduct_shouldUpdateCreateAndDeleteDepots() {

        Long productId = 1L;

        // --- Command setup ---
        ProductDepotCommand existingDepotCmd =
                new ProductDepotCommand(1L, 10L, BigDecimal.valueOf(100), BigDecimal.valueOf(120), true, 10);

        ProductDepotCommand newDepotCmd =
                new ProductDepotCommand(2L, 20L, BigDecimal.valueOf(100), BigDecimal.valueOf(120), true, 10);

        UpdateProductCommand command = new UpdateProductCommand(
                "UpdatedName",
                "UpdatedDesc",
                1L,
                ProductStatus.Active,
                1L,
                1L,
                List.of(existingDepotCmd, newDepotCmd)
        );

        // --- Existing entities ---
        Product product = mock(Product.class);

        Depot depot1 = new Depot(1L, "Eindhoven", "Netherlands", false);
        Depot depot2 = new Depot(2L, "Vught", "Netherlands", false);
        Depot depotToDelete = new Depot(3L, "Tilburg", "Netherlands", false);

        ProductDepot existingPd = mock(ProductDepot.class);
        when(existingPd.getDepot()).thenReturn(depot1);

        ProductDepot pdToDelete = mock(ProductDepot.class);
        when(pdToDelete.getDepot()).thenReturn(depotToDelete);

        List<ProductDepot> existingDepots = List.of(existingPd, pdToDelete);

        // --- Mock repository behavior ---
        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(categoryRepository.findById(any()))
                .thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any()))
                .thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any()))
                .thenReturn(Optional.of(mock(Brand.class)));

        when(productDepotRepository.findByProductId(productId))
                .thenReturn(existingDepots);

        when(depotRepository.findById(2L))
                .thenReturn(Optional.of(depot2));

        when(productRepository.save(any()))
                .thenReturn(product);

        when(productDepotRepository.saveAll(anyList()))
                .thenAnswer(inv -> inv.getArgument(0));

        // --- Act ---
        ProductWithDepotsResult result =
                useCase.updateProduct(productId, command);

        // --- Assert ---
        assertNotNull(result);
        assertEquals(product, result.product());
        assertEquals(2, result.depots().size());

        // ✔ existing depot updated
        verify(existingPd).update(true, 10L, BigDecimal.valueOf(100), BigDecimal.valueOf(120), 10);

        // ✔ product updated
        verify(product).update(
                eq("UpdatedName"),
                eq("UpdatedDesc"),
                any(),
                eq(ProductStatus.Active),
                any(),
                any()
        );

        // ✔ deleted depot
        verify(productDepotRepository).deleteAll(
                argThat(list -> list.size() == 1 &&
                        list.contains(pdToDelete))
        );

        // ✔ saved depots (existing + new)
        verify(productDepotRepository).saveAll(
                argThat(list -> list.size() == 2)
        );
    }
    @Test
    void shouldThrow_whenProductNotFound() {
        when(productRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFound.class,
                () -> useCase.updateProduct(1L, mock(UpdateProductCommand.class)));
    }
    @Test
    void shouldThrow_whenDepotNotFound() {
        Long productId = 1L;

        Product product = mock(Product.class);

        ProductDepotCommand cmd =
                new ProductDepotCommand(99L, 1L, BigDecimal.valueOf(2), BigDecimal.valueOf(3), true, 10);

        UpdateProductCommand command = new UpdateProductCommand(
                "name", "desc", 1L, ProductStatus.Active, 1L, 1L,
                List.of(cmd)
        );

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(categoryRepository.findById(any()))
                .thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any()))
                .thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any()))
                .thenReturn(Optional.of(mock(Brand.class)));

        when(productDepotRepository.findByProductId(productId))
                .thenReturn(List.of());

        when(depotRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.updateProduct(productId, command));
    }

}