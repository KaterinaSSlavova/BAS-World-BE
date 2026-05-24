package basworld.backend.business.impl.product;

import basworld.backend.business.command.ProductDepotCommand;
import basworld.backend.business.command.UpdateProductCommand;
import basworld.backend.business.exception.ProductNotFound;
import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.business.service.StockAlertService;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.product.ProductStatus;
import basworld.backend.domain.repository.*;
import basworld.backend.domain.supplier.Supplier;
import basworld.backend.domain.type.Type;
import basworld.backend.domain.vehicleType.VehicleType;
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
    @Mock private StockAlertService stockAlertService;
    @Mock private VehicleTypeRepository vehicleTypeRepository;
    @Mock private SupplierRepository supplierRepository;

    @InjectMocks
    private UpdateProductUseCaseImpl useCase;

    private UpdateProductCommand buildCommand(List<ProductDepotCommand> depotCommands) {
        return new UpdateProductCommand(
                "UpdatedName", "UpdatedDesc",
                1L, ProductStatus.Active, 1L, 1L, 1L, 1L,
                depotCommands
        );
    }

    private void mockDependencies() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any())).thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any())).thenReturn(Optional.of(mock(Brand.class)));
        when(vehicleTypeRepository.findById(any())).thenReturn(Optional.of(mock(VehicleType.class)));
        when(supplierRepository.findById(any())).thenReturn(Optional.of(mock(Supplier.class)));
    }

    @Test
    void updateProduct_shouldUpdateCreateAndDeleteDepots() {
        Long productId = 1L;

        ProductDepotCommand existingDepotCmd =
                new ProductDepotCommand(1L, 10L, BigDecimal.valueOf(100), BigDecimal.valueOf(120), true, 10, 1L);

        ProductDepotCommand newDepotCmd =
                new ProductDepotCommand(2L, 20L, BigDecimal.valueOf(100), BigDecimal.valueOf(120), true, 10, 1L);

        UpdateProductCommand command = buildCommand(List.of(existingDepotCmd, newDepotCmd));

        Product product = mock(Product.class);

        Depot depot1 = new Depot(1L, "Eindhoven", "Netherlands", false);
        Depot depot2 = new Depot(2L, "Vught", "Netherlands", false);
        Depot depotToDelete = new Depot(3L, "Tilburg", "Netherlands", false);

        ProductDepot existingPd = mock(ProductDepot.class);
        when(existingPd.getDepot()).thenReturn(depot1);

        ProductDepot pdToDelete = mock(ProductDepot.class);
        when(pdToDelete.getDepot()).thenReturn(depotToDelete);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        mockDependencies();

        when(productDepotRepository.findByProductId(productId)).thenReturn(List.of(existingPd, pdToDelete));
        when(depotRepository.findById(2L)).thenReturn(Optional.of(depot2));
        when(productRepository.save(any())).thenReturn(product);
        when(productDepotRepository.saveAll(anyList())).thenAnswer(inv -> inv.getArgument(0));

        ProductWithDepotsResult result = useCase.updateProduct(productId, command);

        assertNotNull(result);
        assertEquals(product, result.product());
        assertEquals(2, result.depots().size());

        verify(existingPd).update(
                eq(10L), eq(BigDecimal.valueOf(100)), eq(BigDecimal.valueOf(120)), eq(10), any(Supplier.class)
        );

        verify(product).update(
                eq("UpdatedName"), eq("UpdatedDesc"),
                any(), eq(ProductStatus.Active),
                any(), any(), any()
        );

        verify(productDepotRepository).deleteAll(
                argThat(list -> list.size() == 1 && list.contains(pdToDelete))
        );

        verify(productDepotRepository).saveAll(
                argThat(list -> list.size() == 2)
        );

        verify(stockAlertService).notifyStockChange();
    }

    @Test
    void shouldThrow_whenRequestIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> useCase.updateProduct(1L, null));
    }

    @Test
    void shouldThrow_whenProductNotFound() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        UpdateProductCommand command = buildCommand(List.of());
        assertThrows(ProductNotFound.class,
                () -> useCase.updateProduct(1L, command));
    }

    @Test
    void shouldThrow_whenCategoryIdIsNull() {
        UpdateProductCommand command = new UpdateProductCommand(
                "name", "desc", 1L, ProductStatus.Active, 1L, null, 1L, 1L, List.of()
        );
        assertThrows(IllegalArgumentException.class,
                () -> useCase.updateProduct(1L, command));
    }

    @Test
    void shouldThrow_whenTypeIdIsNull() {
        UpdateProductCommand command = new UpdateProductCommand(
                "name", "desc", 1L, ProductStatus.Active, null, 1L, 1L, 1L, List.of()
        );
        assertThrows(IllegalArgumentException.class,
                () -> useCase.updateProduct(1L, command));
    }

    @Test
    void shouldThrow_whenBrandIdIsNull() {
        UpdateProductCommand command = new UpdateProductCommand(
                "name", "desc", null, ProductStatus.Active, 1L, 1L, 1L, 1L, List.of()
        );
        assertThrows(IllegalArgumentException.class,
                () -> useCase.updateProduct(1L, command));
    }

    @Test
    void shouldThrow_whenVehicleTypeIdIsNull() {
        UpdateProductCommand command = new UpdateProductCommand(
                "name", "desc", 1L, ProductStatus.Active, 1L, 1L, null, 1L, List.of()
        );
        assertThrows(IllegalArgumentException.class,
                () -> useCase.updateProduct(1L, command));
    }

    @Test
    void shouldThrow_whenSupplierIdIsNull() {
        UpdateProductCommand command = new UpdateProductCommand(
                "name", "desc", 1L, ProductStatus.Active, 1L, 1L, 1L, null, List.of()
        );
        assertThrows(IllegalArgumentException.class,
                () -> useCase.updateProduct(1L, command));
    }

    @Test
    void shouldThrow_whenVehicleTypeNotFound() {
        when(productRepository.findById(any())).thenReturn(Optional.of(mock(Product.class)));
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any())).thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any())).thenReturn(Optional.of(mock(Brand.class)));
        when(vehicleTypeRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> useCase.updateProduct(1L, buildCommand(List.of())));
    }

    @Test
    void shouldThrow_whenSupplierNotFound() {
        when(productRepository.findById(any())).thenReturn(Optional.of(mock(Product.class)));
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any())).thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any())).thenReturn(Optional.of(mock(Brand.class)));
        when(vehicleTypeRepository.findById(any())).thenReturn(Optional.of(mock(VehicleType.class)));
        when(supplierRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> useCase.updateProduct(1L, buildCommand(List.of())));
    }

    @Test
    void shouldThrow_whenDepotNotFound() {
        Long productId = 1L;

        ProductDepotCommand cmd =
                new ProductDepotCommand(99L, 1L, BigDecimal.valueOf(2), BigDecimal.valueOf(3), true, 10, 1L);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mock(Product.class)));
        mockDependencies();
        when(productDepotRepository.findByProductId(productId)).thenReturn(List.of());
        when(depotRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.updateProduct(productId, buildCommand(List.of(cmd))));
    }
}