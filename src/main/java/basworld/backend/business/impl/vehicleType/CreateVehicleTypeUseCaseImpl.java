package basworld.backend.business.impl.vehicleType;

import basworld.backend.business.useCase.vehicleType.CreateVehicleTypeUseCase;
import basworld.backend.domain.repository.VehicleTypeRepository;
import basworld.backend.domain.vehicleType.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CreateVehicleTypeUseCaseImpl implements CreateVehicleTypeUseCase {
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleType create(VehicleType vehicleType) {
        if (vehicleTypeRepository.existsByNameAndArchivedFalse(vehicleType.getName())){
            throw new IllegalArgumentException("This vehicle type already exists!");
        }
        return vehicleTypeRepository.saveVehicleType(vehicleType);
    }
}
