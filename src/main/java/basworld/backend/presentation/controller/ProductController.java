package basworld.backend.presentation.controller;

import basworld.backend.business.command.CreateProductCommand;
import basworld.backend.business.command.ProductDepotCommand;
import basworld.backend.business.useCase.product.CreateProductUseCase;
import basworld.backend.domain.depot.ProductDepot;
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

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    @PostMapping()
    public ResponseEntity<Collection<ProductDepotPublicData>> createProduct(@Validated @RequestBody CreateProductRequest createProductRequest) {
        List<ProductDepotCommand> productDepotCommands = createProductRequest.getProductDepots()
                .stream()
                .map(r -> ProductDepotCommand.builder()
                        .depotId(r.getDepotId())
                        .stockQuantity(r.getStockQuantity())
                        .available(r.isAvailable())
                        .costPrice(r.getCostPrice())
                        .salePrice(r.getSalePrice())
                        .build()
                )
                .toList();
        CreateProductCommand createProductCommand = new CreateProductCommand(createProductRequest.getSku(), createProductRequest.getName(),
                createProductRequest.getDescription(), createProductRequest.getBrandId(), createProductRequest.getStatus(),
                createProductRequest.getTypeId(), createProductRequest.getCategoryId(), productDepotCommands);
        List<ProductDepot> productDepotList = createProductUseCase.createProduct(createProductCommand);
        return ResponseEntity.ok().body(productDepotList.stream().map(ProductDepotDtoMapper::toResponse).toList());
    }
}
