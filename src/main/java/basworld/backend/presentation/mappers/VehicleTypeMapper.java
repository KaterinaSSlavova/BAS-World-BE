package basworld.backend.presentation.mappers;

import basworld.backend.domain.vehicleType.VehicleType;
import basworld.backend.presentation.dto.vehicleType.VehicleTypeRequest;
import basworld.backend.presentation.dto.vehicleType.VehicleTypeResponse;

public class VehicleTypeMapper {
    public static VehicleTypeResponse toVehicleTypeResponse(VehicleType vehicleType) {
        return new  VehicleTypeResponse(
            vehicleType.getId(), vehicleType.getName(), vehicleType.isArchived()
        );
    }
    public static VehicleType toVehicleType(VehicleTypeRequest request) {
        return new VehicleType(request.getName(), request.isArchived());
    }
}
