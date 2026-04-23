package basworld.backend.presentation.controller;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.business.useCase.product.CreateProductUseCase;
import basworld.backend.presentation.dto.CreateProductRequest;
import basworld.backend.presentation.dto.ProductDepotPublicData;
import basworld.backend.presentation.mappers.ProductDepotDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    @PostMapping()
    public ResponseEntity<ProductDepotPublicData> createProduct(@Validated @RequestBody CreateProductRequest createProductRequest) {
        CreateProductCommand createProductCommand = new CreateProductCommand(createProductRequest.getSku(), createProductRequest.getName(),
                createProductRequest.getDescription(), createProductRequest.getBrand(), createProductRequest.getPrice(), createProductRequest.getStatus(),
                createProductRequest.getTypeId(), createProductRequest.getCategoryId(), createProductRequest.getDepotId(), createProductRequest.getStockQuantity(),
                createProductRequest.isAvailable());
        var product = createProductUseCase.createProduct(createProductCommand);
        return ResponseEntity.ok().body(ProductDepotDtoMapper.toResponse(product));
    }
}
