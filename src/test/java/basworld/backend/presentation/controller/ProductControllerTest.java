package basworld.backend.presentation.controller;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ProductControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ProductRepository productRepository;
    @Autowired private DepotRepository depotRepository;
    @Autowired private ProductDepotRepository productDepotRepository;
    @Autowired private TypeRepository typeRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private VehicleTypeRepository vehicleTypeRepository;
    @Autowired private SupplierRepository supplierRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String baseUrl = "/api/products";

    private Brand brand;
    private Category category;
    private Type type;
    private Depot depot;
    private VehicleType vehicleType;
    private Supplier supplier;

    @BeforeEach
    void initData() {
        brand = brandRepository.saveBrand(new Brand("Bosch"));
        category = categoryRepository.save(new Category("Tyres", null));
        type = typeRepository.save(new Type("Physical item", null));
        depot = depotRepository.saveDepot(new Depot(null, "Eindhoven", "NL", false));
        vehicleType = vehicleTypeRepository.saveVehicleType(new VehicleType("Truck", false));
        supplier = supplierRepository.saveSupplier(new Supplier("AutoParts BV", null, false));
    }

    private Map<String, Object> buildDepotItem() {
        Map<String, Object> depotItem = new HashMap<>();
        depotItem.put("depotId", depot.getId());
        depotItem.put("stockQuantity", 10);
        depotItem.put("available", true);
        depotItem.put("costPrice", 100);
        depotItem.put("salePrice", 150);
        depotItem.put("stockThreshold", 10);
        depotItem.put("supplierId", supplier.getId());
        return depotItem;
    }

    private Product savedProduct() {
        return productRepository.save(
                new Product("sku", "name", "desc", brand, ProductStatus.Active, type, category, vehicleType)
        );
    }

    @Test
    void createProduct_success_returnsProduct() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("sku", "SKU1");
        request.put("name", "Product");
        request.put("description", "Desc");
        request.put("brandId", brand.getId());
        request.put("status", "Active");
        request.put("typeId", type.getId());
        request.put("categoryId", category.getId());
        request.put("vehicleTypeId", vehicleType.getId());
        request.put("productDepots", List.of(buildDepotItem()));

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.id").exists())
                .andExpect(jsonPath("$.product.name").value("Product"));
    }

    @Test
    void createProduct_invalid_returns400() throws Exception {
        String json = """
                {
                  "sku": null,
                  "name": null
                }
                """;

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProduct_success() throws Exception {
        Product product = savedProduct();

        mockMvc.perform(get(baseUrl + "/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"));
    }

    @Test
    void getProduct_notFound_returns404() throws Exception {
        mockMvc.perform(get(baseUrl + "/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateProduct_success() throws Exception {
        Product product = savedProduct();

        Map<String, Object> request = new HashMap<>();
        request.put("name", "updated");
        request.put("description", "updated desc");
        request.put("brandId", brand.getId());
        request.put("status", "Active");
        request.put("typeId", type.getId());
        request.put("categoryId", category.getId());
        request.put("vehicleTypeId", vehicleType.getId());
        request.put("supplierId", supplier.getId());
        request.put("productDepots", List.of(buildDepotItem()));

        mockMvc.perform(put(baseUrl + "/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.name").value("updated"));
    }

    @Test
    void updateProduct_invalid_returns400() throws Exception {
        Product product = savedProduct();

        Map<String, Object> request = new HashMap<>();
        request.put("name", "");
        request.put("description", "");
        request.put("brandId", brand.getId());
        request.put("status", "Active");
        request.put("typeId", type.getId());
        request.put("categoryId", category.getId());
        request.put("vehicleTypeId", vehicleType.getId());
        request.put("supplierId", supplier.getId());
        request.put("productDepots", List.of(buildDepotItem()));

        mockMvc.perform(put(baseUrl + "/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateProduct_notFound_returns404() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("name", "updated");
        request.put("description", "updated desc");
        request.put("brandId", brand.getId());
        request.put("status", "Active");
        request.put("typeId", type.getId());
        request.put("categoryId", category.getId());
        request.put("vehicleTypeId", vehicleType.getId());
        request.put("supplierId", supplier.getId());
        request.put("productDepots", List.of());

        mockMvc.perform(put(baseUrl + "/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProductWithDepots_success() throws Exception {
        Product product = savedProduct();

        ProductDepot pd = new ProductDepot(
                product, depot,
                10L,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(150),
                10,
                supplier
        );
        productDepotRepository.save(pd);

        mockMvc.perform(get(baseUrl + "/" + product.getId() + "/with-depots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.depots").isArray());
    }

    @Test
    void getAllProductsWithDepots_success() throws Exception {
        savedProduct();

        mockMvc.perform(get(baseUrl + "/with-depots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}