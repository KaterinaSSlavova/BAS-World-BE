package basworld.backend.presentation.controller;

import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.category.Category;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.product.ProductStatus;
import basworld.backend.domain.repository.*;
import basworld.backend.domain.type.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private ProductDepotRepository productDepotRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    private final String baseUrl = "/api/products";

    private Brand brand;
    private Category category;
    private Type type;
    private Depot depot;

    @BeforeEach
    void initData() {

        brand = brandRepository.saveBrand(
                new Brand("Bosch")
        );

        category = categoryRepository.save(
                new Category("Tyres", null)
        );

        type = typeRepository.save(
                new Type("Physical item", null)
        );

        depot = depotRepository.saveDepot(
                new Depot(null, "Eindhoven", "NL", false)
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

        Map<String, Object> depotItem = new HashMap<>();
        depotItem.put("depotId", depot.getId());
        depotItem.put("stockQuantity", 10);
        depotItem.put("available", true);
        depotItem.put("costPrice", 100);
        depotItem.put("salePrice", 150);

        request.put("productDepots", List.of(depotItem));

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
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
    void getAllProducts_returnsList() throws Exception {

        productRepository.save(new Product("sku1", "p1", "desc", brand, ProductStatus.Active, type, category));
        productRepository.save(new Product("sku2", "p2", "desc", brand, ProductStatus.Active, type, category));

        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }


    @Test
    void getProduct_success() throws Exception {

        Product product = productRepository.save(
                new Product("sku", "name", "desc", brand, ProductStatus.Active, type, category)
        );

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

        Product product = productRepository.save(
                new Product("sku", "old", "desc", brand, ProductStatus.Active, type, category)
        );

        Map<String, Object> request = new HashMap<>();
        request.put("name", "updated");
        request.put("description", "updated desc");
        request.put("brandId", brand.getId());
        request.put("status", "Active");
        request.put("typeId", type.getId());
        request.put("categoryId", category.getId());
        request.put("productDepots", List.of());

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(put(baseUrl + "/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.name").value("updated"));
    }


    @Test
    void updateProduct_invalid_returns400() throws Exception {

        Product product = productRepository.save(
                new Product("sku", "old", "desc", brand, ProductStatus.Active, type, category)
        );

        String json = """
        {
          "name": "",
          "description": "",
          "brandId": null,
          "status": "ACTIVE",
          "typeId": 1,
          "categoryId": 1,
          "productDepots": []
        }
        """;

        mockMvc.perform(put(baseUrl + "/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProductWithDepots_success() throws Exception {

        Product product = productRepository.save(
                new Product("sku", "name", "desc", brand, ProductStatus.Active, type, category)
        );

        ProductDepot pd = new ProductDepot(
                product,
                depot,
                true,
                10L,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(150)
        );

        productDepotRepository.save(pd);

        mockMvc.perform(get(baseUrl + "/" + product.getId() + "/with-depots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.depots").isArray());
    }
}