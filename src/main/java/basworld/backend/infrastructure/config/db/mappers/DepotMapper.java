package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.depot.Depot;
import basworld.backend.infrastructure.config.db.entity.DepotEntity;


public class DepotMapper {
    public static DepotEntity ToEntity(Depot depot){
        return DepotEntity.builder()
                .id(depot.getId())
                .depotName(depot.getDepotName())
                .Location(depot.getLocation())
                .build();
    }

    public static Depot ToDomain(DepotEntity entity){
        return Depot.builder()
                .id(entity.getId())
                .depotName(entity.getDepotName())
                .location(entity.getLocation())
                .build();
    }
}
