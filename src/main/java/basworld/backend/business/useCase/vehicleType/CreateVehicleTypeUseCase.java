package basworld.backend.business.useCase.vehicleType;

import basworld.backend.domain.vehicleType.VehicleType;
import basworld.backend.presentation.dto.vehicleType.VehicleTypeResponse;

public interface CreateVehicleTypeUseCase {
    VehicleType create(VehicleType vehicleType);
}
