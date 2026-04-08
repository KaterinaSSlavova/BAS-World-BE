package basworld.backend.presentation.controller;

import basworld.backend.business.useCase.CreateDepotUseCase;
import basworld.backend.business.useCase.GetDepotUseCase;
import basworld.backend.domain.depot.Depot;
import basworld.backend.presentation.dto.CreateDepotRequest;
import basworld.backend.presentation.dto.DepotResponse;
import basworld.backend.presentation.mappers.DepotMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/depots")
@RequiredArgsConstructor
public class DepotController {
    private final CreateDepotUseCase createDepotUseCase;
    private final GetDepotUseCase getDepotUseCase;

    @PostMapping
    public ResponseEntity<DepotResponse> createDepot(@RequestBody @Valid CreateDepotRequest request) {
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
}
