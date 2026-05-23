package basworld.backend.business.useCase.vehicleType;

import basworld.backend.domain.vehicleType.VehicleType;

public interface GetVehicleTypeByIdUseCase {
    VehicleType getVehicleTypeById(Long id);
}
