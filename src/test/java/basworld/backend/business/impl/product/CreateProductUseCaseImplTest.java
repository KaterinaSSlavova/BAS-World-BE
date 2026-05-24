package basworld.backend.business.impl.product;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.business.command.ProductDepotCommand;
import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.product.ProductStatus;
import basworld.backend.domain.repository.*;
import basworld.backend.domain.supplier.Supplier;
import basworld.backend.domain.type.Type;
import basworld.backend.domain.vehicleType.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class CreateProductUseCaseImplTest {

    @Mock private ProductRepository productRepository;
    @Mock private CategoryRepository categoryRepository;
    @Mock private TypeRepository typeRepository;
    @Mock private DepotRepository depotRepository;
    @Mock private ProductDepotRepository productDepotRepository;
    @Mock private BrandRepository brandRepository;
    @Mock private VehicleTypeRepository vehicleTypeRepository;
    @Mock private SupplierRepository supplierRepository;

    @InjectMocks
    private CreateProductUseCaseImpl useCase;

    private CreateProductCommand request;

    @BeforeEach
    void setup() {
        ProductDepotCommand depotCommand = new ProductDepotCommand(
                1L, 10L, BigDecimal.valueOf(100), BigDecimal.valueOf(120), true, 10, 1L);

        request = new CreateProductCommand(
                "SKU123",
                "ProductName",
                "Description",
                1L,
                ProductStatus.Active,
                1L,
                1L,
                1L,
                List.of(depotCommand)
        );
    }

    @Test
    void createProduct_shouldCreateProductWithDepots() {
        var category = mock(Category.class);
        var type = mock(Type.class);
        var brand = mock(Brand.class);
        var vehicleType = mock(VehicleType.class);
        var supplier = mock(Supplier.class);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(typeRepository.findById(1L)).thenReturn(Optional.of(type));
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(vehicleTypeRepository.findById(1L)).thenReturn(Optional.of(vehicleType));
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        Product savedProduct = new Product(
                "SKU123", "ProductName", "Description",
                brand, ProductStatus.Active, type, category, vehicleType
        );
        savedProduct.setId(1L);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Depot depot = new Depot(1L, "depot", "Netherlands", false);
        when(depotRepository.findByMultipleIds(List.of(1L))).thenReturn(List.of(depot));
        when(productDepotRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        ProductWithDepotsResult result = useCase.createProduct(request);

        assertNotNull(result);
        assertEquals(savedProduct, result.product());
        assertEquals(1, result.depots().size());

        verify(productRepository).save(any(Product.class));
        verify(productDepotRepository).saveAll(anyList());
    }

    @Test
    void createProduct_shouldThrowException_whenRequestIsNull() {
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(null));
    }

    @Test
    void shouldThrow_whenCategoryIdIsNull() {
        request.setCategoryId(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenTypeIdIsNull() {
        request.setTypeId(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenBrandIdIsNull() {
        request.setBrandId(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenVehicleTypeIdIsNull() {
        request.setVehicleTypeId(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenCategoryNotFound() {
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenTypeNotFound() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenBrandNotFound() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any())).thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenVehicleTypeNotFound() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any())).thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any())).thenReturn(Optional.of(mock(Brand.class)));
        when(vehicleTypeRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenSupplierNotFound() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any())).thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any())).thenReturn(Optional.of(mock(Brand.class)));
        when(vehicleTypeRepository.findById(any())).thenReturn(Optional.of(mock(VehicleType.class)));
        when(productRepository.save(any())).thenReturn(mock(Product.class));
        when(depotRepository.findByMultipleIds(any())).thenReturn(List.of(new Depot(1L, "depot", "Netherlands", false)));
        when(supplierRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> useCase.createProduct(request));
    }

    @Test
    void shouldThrow_whenDepotNotFound() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(Category.class)));
        when(typeRepository.findById(any())).thenReturn(Optional.of(mock(Type.class)));
        when(brandRepository.findById(any())).thenReturn(Optional.of(mock(Brand.class)));
        when(vehicleTypeRepository.findById(any())).thenReturn(Optional.of(mock(VehicleType.class)));
        when(productRepository.save(any())).thenReturn(mock(Product.class));
        when(depotRepository.findByMultipleIds(any())).thenReturn(List.of());
        assertThrows(RuntimeException.class, () -> useCase.createProduct(request));
    }
}