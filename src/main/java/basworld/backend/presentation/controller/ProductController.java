package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.CreateProductUseCase;
import basworld.backend.presentation.dto.CreateProductRequest;
import basworld.backend.presentation.dto.ProductPublicData;
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
    public ResponseEntity<ProductPublicData> createProduct(@Validated @RequestBody CreateProductRequest createProductRequest) {
        var result = createProductUseCase.createProduct(createProductRequest);
        return ResponseEntity.ok().body(result);
    }
}
