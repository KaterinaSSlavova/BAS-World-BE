package basworld.backend.presentation.controller;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.business.command.ProductDepotCommand;
import basworld.backend.business.command.UpdateProductCommand;
import basworld.backend.business.result.ProductWithDepotsResult;
import basworld.backend.business.useCase.product.*;
import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;
import basworld.backend.presentation.dto.product.*;
import basworld.backend.presentation.mappers.ProductDtoMapper;
import basworld.backend.presentation.mappers.ProductWithDepotsDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetAllProductUseCase getAllProductUseCase;
    private final GetAllProductWithDepotsUseCase getAllProductWithDepotsUseCase;
    private final GetProductWithDepotsUseCase getProductWithDepotsUseCase;
    @PostMapping()
    public ResponseEntity<ProductWithDepotsResponse> createProduct(@Validated @RequestBody CreateProductRequest createProductRequest) {
        List<ProductDepotCommand> productDepotCommands = createProductRequest.getProductDepots()
                .stream()
                .map(r -> ProductDepotCommand.builder()
                        .depotId(r.getDepotId())
                        .stockQuantity(r.getStockQuantity())
                        .available(r.isAvailable())
                        .costPrice(r.getCostPrice())
                        .salePrice(r.getSalePrice())
                        .stockThreshold(r.getStockThreshold())
                        .supplierId(r.getSupplierId())
                        .build()
                )
                .toList();
        CreateProductCommand createProductCommand = new CreateProductCommand(createProductRequest.getSku(), createProductRequest.getName(),
                createProductRequest.getDescription(), createProductRequest.getBrandId(), createProductRequest.getStatus(),
                createProductRequest.getTypeId(), createProductRequest.getCategoryId(), createProductRequest.getVehicleTypeId(),  productDepotCommands);
        ProductWithDepotsResult productWithDepotsResult = createProductUseCase.createProduct(createProductCommand);
        return ResponseEntity.ok().body(ProductWithDepotsDtoMapper.toResponse(productWithDepotsResult.product(),
                productWithDepotsResult.depots()));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductWithDepotsResponse> updateProduct(@PathVariable("productId") Long productId,
                                                                            @Validated @RequestBody UpdateProductRequest updateProductRequest){
        List<ProductDepotCommand> productDepotCommands = updateProductRequest.getProductDepots()
                .stream()
                .map(r -> ProductDepotCommand.builder()
                        .depotId(r.getDepotId())
                        .stockQuantity(r.getStockQuantity())
                        .available(r.isAvailable())
                        .costPrice(r.getCostPrice())
                        .salePrice(r.getSalePrice())
                        .supplierId(r.getSupplierId())
                        .build()
                )
                .stream().map(r -> {
                    System.out.println("stockThreshold from request: " + r.getStockThreshold());
                    return ProductDepotCommand.builder()
                            .depotId(r.getDepotId())
                            .stockQuantity(r.getStockQuantity())
                            .available(r.isAvailable())
                            .costPrice(r.getCostPrice())
                            .salePrice(r.getSalePrice())
                            .stockThreshold(r.getStockThreshold())
                            .build();
                })
                .toList();

        UpdateProductCommand updateProductCommand = new UpdateProductCommand(
                updateProductRequest.getName(),
                updateProductRequest.getDescription(),
                updateProductRequest.getBrandId(),
                updateProductRequest.getStatus(),
                updateProductRequest.getTypeId(),
                updateProductRequest.getCategoryId(),
                updateProductRequest.getVehicleTypeId(),
                updateProductRequest.getSupplierId(),
                productDepotCommands
        );

        ProductWithDepotsResult productWithDepotsResult = updateProductUseCase.updateProduct(productId, updateProductCommand);
        return ResponseEntity.ok().body(ProductWithDepotsDtoMapper.toResponse(productWithDepotsResult.product(),
                productWithDepotsResult.depots()));
    }

    @GetMapping
    public ResponseEntity<List<ProductPublicData>> getAllProducts() {
        List<Product> products = getAllProductUseCase.getAll();
        return ResponseEntity.ok().body(products.stream().map(ProductDtoMapper::toProductPublicData).toList());
    }
    @GetMapping("/with-depots")
    public ResponseEntity<List<ProductWithDepotsResponse>> getAllProductsWithDepots() {
        Map<Product, List<ProductDepot>> productListMap = getAllProductWithDepotsUseCase.getAll();

        List<ProductWithDepotsResponse> response =
                productListMap.entrySet()
                        .stream()
                        .map(e -> ProductWithDepotsDtoMapper.toResponse(e.getKey(), e.getValue()))
                        .toList();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductPublicData> getProduct(@PathVariable("id") Long productId) {
        var product = getProductUseCase.getProductById(productId);
        return ResponseEntity.ok().body(ProductDtoMapper.toProductPublicData(product));
    }

    @GetMapping("/{id}/with-depots")
    public ResponseEntity<ProductWithDepotsResponse> getProductWithDepots(@PathVariable("id") Long productId) {
        var productWithDepots = getProductWithDepotsUseCase.getProductWithDepots(productId);
        return ResponseEntity.ok().body(ProductWithDepotsDtoMapper.toResponse(productWithDepots.product(), productWithDepots.depots()));
    }
}
