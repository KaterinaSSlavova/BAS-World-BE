package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.Depot;
import basworld.backend.presentation.dto.CreateDepotRequest;
import basworld.backend.presentation.dto.DepotResponse;

public class DepotMapper {
    public static DepotResponse toResponse(Depot depot) {
        return DepotResponse.builder()
                .id(depot.getId()).depotName(depot.getDepotName())
                .location(depot.getLocation()).build();
    }

    public static CreateDepotRequest toCreateRequest(Depot depot) {
        return CreateDepotRequest.builder()
                .depotName(depot.getDepotName()).location(depot.getLocation()).build();
    }
}
