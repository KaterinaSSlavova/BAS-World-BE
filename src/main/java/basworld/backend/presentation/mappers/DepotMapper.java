package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.Depot;
import basworld.backend.presentation.dto.CreateDepotRequest;
import basworld.backend.presentation.dto.DepotResponse;

public class DepotMapper {
    public static Depot toDomain(CreateDepotRequest depot) {
        return Depot.builder()
                .depotName(depot.getDepotName()).location(depot.getLocation()).build();
    }

    public static DepotResponse toResponse(Depot depot) {
        return DepotResponse.builder().id(depot.getId())
                .depotName(depot.getDepotName()).location(depot.getLocation()).build();
    }
}
