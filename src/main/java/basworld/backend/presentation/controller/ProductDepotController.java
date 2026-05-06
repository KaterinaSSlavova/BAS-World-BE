package basworld.backend.presentation.controller;
import basworld.backend.business.useCase.*;
import basworld.backend.business.useCase.category.GetCategoryUseCase;
import basworld.backend.business.useCase.depot.GetDepotUseCase;
import basworld.backend.business.useCase.product.GetProductUseCase;
import basworld.backend.business.useCase.product.UpdateProductUseCase;
import basworld.backend.business.useCase.type.GetTypeUseCase;
import basworld.backend.presentation.dto.product.ProductDepotPublicData;
import basworld.backend.presentation.mappers.ProductDepotDtoMapper;
import lombok.RequiredArgsConstructor;
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


//    @GetMapping
//    public List<ProductWithDepotsResponse> getAll() {
//        return getAllProductDepotUseCase.execute()
//                .stream()
//                .map(ProductWithDepotsDtoMapper::toResponse)
//                .toList();
//    }

    @GetMapping("/search")
    public List<ProductDepotPublicData> search(@RequestParam String query) {
        return searchProductUseCase.execute(query)
                .stream()
                .map(ProductDepotDtoMapper::toResponse)
                .toList();
    }
}