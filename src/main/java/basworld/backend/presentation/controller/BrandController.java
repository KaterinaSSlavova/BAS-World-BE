package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.brand.*;
import basworld.backend.domain.brand.Brand;
import basworld.backend.presentation.dto.brand.BrandResponse;
import basworld.backend.presentation.dto.brand.BrandRequest;
import basworld.backend.presentation.mappers.BrandMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
    private final CreateBrandUseCase createBrandUseCase;
    private final UpdateBrandUseCase updateBrandUseCase;
    private final GetBrandUseCase getBrandUseCase;
    private final GetAllBrandsUseCase getAllBrandsUseCase;
    private final ArchiveBrandUseCase archiveBrandUseCase;

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@RequestBody @Valid BrandRequest request){
        BrandResponse response = BrandMapper.toResponse(createBrandUseCase.createBrand(BrandMapper.toDomain(request)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable("id")final long id){
        BrandResponse response = BrandMapper.toResponse(getBrandUseCase.getBrandById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllBrands(){
        List<BrandResponse> response = getAllBrandsUseCase.findAll().stream().map(BrandMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}/archive")
    public ResponseEntity<Void> archiveBrand(@PathVariable("id")final long id){
        archiveBrandUseCase.archiveBrand(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable("id")final long id, @RequestBody @Valid BrandRequest request){
        Brand brand = BrandMapper.toDomain(request);
        brand.setId(id);
        BrandResponse response = BrandMapper.toResponse(updateBrandUseCase.updateBrand(brand));
        return ResponseEntity.ok(response);
    }
}
