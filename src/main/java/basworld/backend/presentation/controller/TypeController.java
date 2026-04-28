package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.type.*;
import basworld.backend.domain.type.Type;
import basworld.backend.presentation.dto.type.TypeRequest;
import basworld.backend.presentation.dto.type.TypeResponse;
import basworld.backend.presentation.mappers.TypeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeController {
    private final CreateTypeUseCase createTypeUseCase;
    private final UpdateTypeUseCase updateTypeUseCase;
    private final GetTypeUseCase getTypeUseCase;
    private final GetAllTypesUseCase getAllTypesUseCase;
    private final ArchiveTypeUseCase archiveTypeUseCase;

    @PostMapping
    public ResponseEntity<TypeResponse> createType(@RequestBody @Valid TypeRequest request) {
        TypeResponse response = TypeMapper.toResponse(
                createTypeUseCase.createType(TypeMapper.toDomain(request), request.getParentId())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeResponse> getTypeById(@PathVariable("id") final long id) {
        TypeResponse response = TypeMapper.toResponse(getTypeUseCase.findById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TypeResponse>> getAllTypes() {
        List<TypeResponse> response = getAllTypesUseCase.getAllTypes()
                .stream()
                .map(TypeMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeResponse> updateType(@PathVariable("id") final long id,
                                                   @RequestBody @Valid TypeRequest request) {
        TypeResponse response = TypeMapper.toResponse(
                updateTypeUseCase.updateType(id, TypeMapper.toDomain(request), request.getParentId())
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Void> archiveType(@PathVariable("id") final long id) {
        archiveTypeUseCase.archiveType(id);
        return ResponseEntity.noContent().build();
    }
}