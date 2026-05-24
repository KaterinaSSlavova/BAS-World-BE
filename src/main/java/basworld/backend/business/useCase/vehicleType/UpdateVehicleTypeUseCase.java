package basworld.backend.business.useCase.vehicleType;

import basworld.backend.domain.vehicleType.VehicleType;

public interface UpdateVehicleTypeUseCase {
    VehicleType updateVehicleType(VehicleType vehicleType, Long id);
}
