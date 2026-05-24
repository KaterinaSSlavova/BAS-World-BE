package basworld.backend.stock_alerts;

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
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ProductDepotControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ProductRepository productRepository;
    @Autowired private DepotRepository depotRepository;
    @Autowired private ProductDepotRepository productDepotRepository;
    @Autowired private TypeRepository typeRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private VehicleTypeRepository vehicleTypeRepository;
    @Autowired private SupplierRepository supplierRepository;

    private final String baseUrl = "/api/product-depots";

    private Product product;
    private Depot depot;
    private Supplier supplier;

    @BeforeEach
    void initData() {
        Brand brand = brandRepository.saveBrand(new Brand("Bosch"));
        Category category = categoryRepository.save(new Category("Tyres", null));
        Type type = typeRepository.save(new Type("Physical item", null));
        VehicleType vehicleType = vehicleTypeRepository.saveVehicleType(new VehicleType("Truck", false));
        supplier = supplierRepository.saveSupplier(new Supplier("AutoParts BV", null, false));
        depot = depotRepository.saveDepot(new Depot(null, "Eindhoven", "NL", false));

        product = productRepository.save(
                new Product("SKU-001", "Truck Tyre", "A truck tyre", brand, ProductStatus.Active, type, category, vehicleType)
        );
    }

    private ProductDepot saveProductDepot(long stockQuantity, int threshold) {
        ProductDepot pd = new ProductDepot(
                product, depot,
                stockQuantity,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(150),
                threshold,
                supplier
        );
        return productDepotRepository.save(pd);
    }


    @Test
    void search_shouldReturnResults_whenQueryMatches() throws Exception {
        saveProductDepot(10, 5);

        mockMvc.perform(get(baseUrl + "/search").param("query", "Truck"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void search_shouldReturnEmpty_whenQueryDoesNotMatch() throws Exception {
        saveProductDepot(10, 5);

        mockMvc.perform(get(baseUrl + "/search").param("query", "xyznonexistent123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void search_shouldReturn400_whenQueryParamMissing() throws Exception {
        mockMvc.perform(get(baseUrl + "/search"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getStockAlerts_shouldReturnEmptyList_whenNoLowStock() throws Exception {
        saveProductDepot(100, 5); // stock well above threshold

        mockMvc.perform(get(baseUrl + "/stock-alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[?(@.productName == 'Truck Tyre')]").isEmpty());
    }

    @Test
    void getStockAlerts_shouldReturnAlerts_whenStockIsBelowThreshold() throws Exception {
        saveProductDepot(3, 10); // stock below threshold → low stock

        mockMvc.perform(get(baseUrl + "/stock-alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[?(@.status == 'LOW_STOCK')]").isNotEmpty());
    }

    @Test
    void getStockAlerts_shouldReturnOutOfStock_whenStockIsZero() throws Exception {
        saveProductDepot(0, 10); // stock is zero → out of stock

        mockMvc.perform(get(baseUrl + "/stock-alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[?(@.status == 'OUT_OF_STOCK')]").isNotEmpty());
    }

    @Test
    void getStockAlerts_shouldReturnOk_whenNoProductDepots() throws Exception {
        mockMvc.perform(get(baseUrl + "/stock-alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}