package basworld.backend.presentation.controller;
import basworld.backend.business.useCase.GetAllProductDepotUseCase;
import basworld.backend.presentation.dto.ProductDepotResponse;
import basworld.backend.presentation.mappers.ProductDepotResponseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-depots")
public class ProductDepotController {

    private final GetAllProductDepotUseCase getAllProductDepotUseCase;

    public ProductDepotController(GetAllProductDepotUseCase getAllProductDepotUseCase) {
        this.getAllProductDepotUseCase = getAllProductDepotUseCase;
    }

    @GetMapping
    public List<ProductDepotResponse> getAll() {
        return getAllProductDepotUseCase.execute()
                .stream()
                .map(ProductDepotResponseMapper::toResponse)
                .toList();
    }
}