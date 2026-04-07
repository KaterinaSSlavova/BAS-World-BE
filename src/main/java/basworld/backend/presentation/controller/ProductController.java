package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.CreateProductUseCase;
import basworld.backend.presentation.dto.CreateProductRequest;
import basworld.backend.presentation.dto.ProductDepotResponse;
import basworld.backend.presentation.mappers.ProductDepotResponseMapper;
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
    public ResponseEntity<ProductDepotResponse> createProduct(@Validated @RequestBody CreateProductRequest createProductRequest) {
        var product = createProductUseCase.createProduct(createProductRequest);
        return ResponseEntity.ok().body(ProductDepotResponseMapper.toResponse(product));
    }
}
