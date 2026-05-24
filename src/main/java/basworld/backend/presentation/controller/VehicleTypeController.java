package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.supplier.*;
import basworld.backend.business.useCase.vehicleType.*;
import basworld.backend.presentation.dto.vehicleType.VehicleTypeRequest;
import basworld.backend.presentation.dto.vehicleType.VehicleTypeResponse;
import basworld.backend.presentation.mappers.VehicleTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/vehicle-types")
@RequiredArgsConstructor
public class VehicleTypeController {
    private final CreateVehicleTypeUseCase createVehicleTypeUseCase;
    private final GetAllVehicleTypeUseCase getAllVehicleTypeUseCase;
    private final GetVehicleTypeByIdUseCase getVehicleTypeByIdUseCase;
    private final ArchiveVehicleTypeUseCase archiveVehicleTypeUseCase;
    private final UpdateVehicleTypeUseCase updateVehicleTypeUseCase;
    @PostMapping
    public ResponseEntity<VehicleTypeResponse> createVehicleType(@RequestBody VehicleTypeRequest request){
        var vehicleType = VehicleTypeMapper.toVehicleType(request);
        var result = VehicleTypeMapper.toVehicleTypeResponse(createVehicleTypeUseCase.create(vehicleType));
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VehicleTypeResponse> getVehicleType(@PathVariable("id")final long id){
        var result = VehicleTypeMapper.toVehicleTypeResponse(getVehicleTypeByIdUseCase.getVehicleTypeById(id));
        return ResponseEntity.ok().body(result);
    }
    @GetMapping()
    public ResponseEntity<Collection<VehicleTypeResponse>> getAllVehicleType(){
        var result = getAllVehicleTypeUseCase.getAllVehicleType().stream().map(VehicleTypeMapper::toVehicleTypeResponse).toList();
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<VehicleTypeResponse> updateVehicleType(@PathVariable("id")final long id, @RequestBody VehicleTypeRequest request){
        var vehicleType = VehicleTypeMapper.toVehicleType(request);
        var result = VehicleTypeMapper.toVehicleTypeResponse(updateVehicleTypeUseCase.updateVehicleType(vehicleType, id));
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("{id}/archive")
    public ResponseEntity<Void> archiveVehicleType(@PathVariable("id")final long id){
        archiveVehicleTypeUseCase.archive(id);
        return ResponseEntity.ok().build();
    }
}