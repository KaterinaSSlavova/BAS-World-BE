package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.supplier.*;
import basworld.backend.presentation.dto.supplier.SupplierRequest;
import basworld.backend.presentation.dto.supplier.SupplierResponse;
import basworld.backend.presentation.mappers.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final CreateSupplierUseCase createSupplierUseCase;
    private final GetSupplierByIdUseCase  getSupplierByIdUseCase;
    private final GetAllSupplierUseCase getAllSupplierUseCase;
    private final ArchiveSupplierUseCase archiveSupplierUseCase;
    private final UpdateSupplierUseCase updateSupplierUseCase;
    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody SupplierRequest supplierRequest){
        var supplier = SupplierMapper.toSupplier(supplierRequest);
        var result = SupplierMapper.toSupplierResponse(createSupplierUseCase.createSupplier(supplier));
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplier(@PathVariable("id")final long id){
        var result = SupplierMapper.toSupplierResponse(getSupplierByIdUseCase.getSupplierById(id));
        return ResponseEntity.ok().body(result);
    }
    @GetMapping()
    public ResponseEntity<Collection<SupplierResponse>> getAllSuppliers(){
        var result = getAllSupplierUseCase.getAllSuppliers().stream().map(SupplierMapper::toSupplierResponse).toList();
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> updateSupplier(@PathVariable("id")final long id, @RequestBody SupplierRequest supplierRequest){
        var supplier = SupplierMapper.toSupplier(supplierRequest);
        var result = SupplierMapper.toSupplierResponse(updateSupplierUseCase.updateSupplier(supplier, id));
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("{id}/archive")
    public ResponseEntity<Void> archiveSupplier(@PathVariable("id")final long id){
        archiveSupplierUseCase.archiveSupplier(id);
        return ResponseEntity.ok().build();
    }
}
