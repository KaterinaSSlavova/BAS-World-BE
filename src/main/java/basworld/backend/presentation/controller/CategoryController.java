package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.category.*;
import basworld.backend.domain.category.Category;
import basworld.backend.presentation.dto.category.CategoryRequest;
import basworld.backend.presentation.dto.category.CategoryResponse;
import basworld.backend.presentation.mappers.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final ArchiveCategoryUseCase archiveCategoryUseCase;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest request) {
        CategoryResponse response = CategoryMapper.toResponse(
                createCategoryUseCase.createCategory(CategoryMapper.toDomain(request), request.getParentId())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") final long id) {
        CategoryResponse response = CategoryMapper.toResponse(getCategoryUseCase.findById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> response = getAllCategoriesUseCase.getAllCategories()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") final long id,
                                                           @RequestBody @Valid CategoryRequest request) {
        Category category = CategoryMapper.toDomain(request);
        CategoryResponse response = CategoryMapper.toResponse(
                updateCategoryUseCase.updateCategory(id, category, request.getParentId())
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Void> archiveCategory(@PathVariable("id") final long id) {
        archiveCategoryUseCase.archiveCategory(id);
        return ResponseEntity.noContent().build();
    }
}