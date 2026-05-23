package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.vehicleType.VehicleType;
import basworld.backend.infrastructure.config.db.entity.VehicleTypeEntity;

public class VehicleTypeMapper {
    public static VehicleTypeEntity toEntity(VehicleType   vehicleType) {
        return VehicleTypeEntity.builder()
                .id(vehicleType.getId())
                .name(vehicleType.getName())
                .archived(vehicleType.isArchived())
                .build();
    }

    public static VehicleType toDomain(VehicleTypeEntity entity) {
        return new VehicleType
                (entity.getId(), entity.getName(), entity.isArchived());
    }
}
