package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.depot.Depot;
import basworld.backend.infrastructure.config.db.entity.DepotEntity;

import java.util.ArrayList;

public class DepotMapper {
    public static DepotEntity toEntity(Depot depot){
        return DepotEntity.builder()
                .id(depot.getId())
                .depotName(depot.getDepotName())
                .Location(depot.getLocation())
                .productDepots(new ArrayList<>())
                .archived(depot.isArchived())
                .build();
    }

    public static Depot toDomain(DepotEntity entity){
        return Depot.builder()
                .id(entity.getId())
                .depotName(entity.getDepotName())
                .location(entity.getLocation())
                .archived(entity.isArchived())
                .build();
    }
}
