package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.depot.*;
import basworld.backend.domain.depot.Depot;
import basworld.backend.presentation.dto.depot.DepotRequest;
import basworld.backend.presentation.dto.depot.DepotOverviewDTO;
import basworld.backend.presentation.dto.depot.DepotOverviewResponse;
import basworld.backend.presentation.dto.depot.DepotResponse;
import basworld.backend.presentation.mappers.DepotMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/depots")
@RequiredArgsConstructor
public class DepotController {
    private final CreateDepotUseCase createDepotUseCase;
    private final GetDepotUseCase getDepotUseCase;
    private final GetAllDepotsUseCase getAllDepotsUseCase;
    private final UpdateDepotUseCase updateDepotUseCase;
    private final ArchiveDepotUseCase archiveDepotUseCase;

    @PostMapping
    public ResponseEntity<DepotResponse> createDepot(@RequestBody @Valid DepotRequest request) {
        Depot newDepot = DepotMapper.toDomain(request);
        Depot savedDepot = createDepotUseCase.createDepot(newDepot);
        DepotResponse response = DepotMapper.toResponse(savedDepot);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepotResponse> getDepotById(@PathVariable("id")final long id) {
        Depot depot  = getDepotUseCase.getDepotById(id);
        DepotResponse response = DepotMapper.toResponse(depot);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<DepotOverviewResponse> getDepotOverview(){
        List<DepotOverviewDTO> depotsDTO = getAllDepotsUseCase.getDepotOverview();
        DepotOverviewResponse response = new DepotOverviewResponse(depotsDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}/archive")
    public ResponseEntity<Void> archiveDepot(@PathVariable("id")final long id){
        archiveDepotUseCase.archiveDepot(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DepotResponse> updateDepot
            (@PathVariable("id")final long id, @RequestBody @Valid DepotRequest request){
        Depot depot = DepotMapper.toDomain(request);
        depot.setId(id);
        DepotResponse response = DepotMapper.toResponse(updateDepotUseCase.updateDepot(depot));
        return ResponseEntity.ok(response);
    }
}
