package basworld.backend.presentation.controller;
import basworld.backend.business.useCase.*;
import basworld.backend.business.useCase.category.GetCategoryUseCase;
import basworld.backend.business.useCase.depot.GetDepotUseCase;
import basworld.backend.business.useCase.type.GetTypeUseCase;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.presentation.dto.ProductDepotResponse;
import basworld.backend.presentation.dto.UpdateProductRequest;
import basworld.backend.presentation.mappers.ProductDepotResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-depots")
@RequiredArgsConstructor
public class ProductDepotController {

    private final GetAllProductDepotUseCase getAllProductDepotUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetDepotUseCase getDepotUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final GetTypeUseCase getTypeUseCase;
    private final SearchProductUseCase searchProductUseCase;


    @GetMapping
    public List<ProductDepotResponse> getAll() {
        return getAllProductDepotUseCase.execute()
                .stream()
                .map(ProductDepotResponseMapper::toResponse)
                .toList();
    }

    @PutMapping("/{productId}/depots/{depotId}")
    public ResponseEntity<ProductDepotResponse> updateProduct(
            @PathVariable("productId")final long productId,
            @PathVariable("depotId") final long depotId,
            @RequestBody @Valid UpdateProductRequest request) {

        Product existingProduct = getProductUseCase.getProductById(productId);

        Product product = new Product(
                existingProduct.getId(),
                existingProduct.getSku(),
                request.getName(),
                request.getDescription(),
                null,
                request.getStatus(),
                getTypeUseCase.findById(request.getTypeId()),
                getCategoryUseCase.findById(request.getCategoryId())
        );

        ProductDepot productDepot = ProductDepot.builder()
                .product(product)
                .depot(getDepotUseCase.getDepotById(depotId))
                .isAvailable(request.getAvailable())
                .stockQuantity(request.getStockQuantity())
                .build();

        ProductDepot updated = updateProductUseCase.updateProduct(productDepot);

        return ResponseEntity.ok(ProductDepotResponseMapper.toResponse(updated));
    }

    @GetMapping("/search")
    public List<ProductDepotResponse> search(@RequestParam String query) {
        return searchProductUseCase.execute(query)
                .stream()
                .map(ProductDepotResponseMapper::toResponse)
                .toList();
    }
}