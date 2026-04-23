package basworld.backend.presentation.mappers;

import basworld.backend.domain.depot.Depot;
import basworld.backend.presentation.dto.depot.DepotRequest;
import basworld.backend.presentation.dto.depot.DepotResponse;

public class DepotMapper {
    public static Depot toDomain(DepotRequest depot) {
        return Depot.builder()
                .depotName(depot.getDepotName()).location(depot.getLocation()).build();
    }

    public static DepotResponse toResponse(Depot depot) {
        return DepotResponse.builder().id(depot.getId())
                .depotName(depot.getDepotName()).location(depot.getLocation()).build();
    }
}
