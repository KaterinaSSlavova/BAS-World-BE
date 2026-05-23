package basworld.backend.business.impl.vehicleType;

import basworld.backend.business.useCase.vehicleType.UpdateVehicleTypeUseCase;
import basworld.backend.domain.repository.VehicleTypeRepository;
import basworld.backend.domain.vehicleType.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UpdateVehicleTypeUseCaseImpl implements UpdateVehicleTypeUseCase {
    private final VehicleTypeRepository vehicleTypeRepository;
    public VehicleType updateVehicleType(VehicleType vehicleType, Long id){
        if (id <= 0){
            throw new IllegalArgumentException("Invalid id!");
        }
        var oldVehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("VehicleType not found!"));
        if(!oldVehicleType.getName().equalsIgnoreCase(vehicleType.getName())
                && vehicleTypeRepository.existsByNameAndArchivedFalse(vehicleType.getName())) {
            throw new IllegalArgumentException("This vehicle type already exists!");
        }
        oldVehicleType.update(vehicleType.getName(), vehicleType.isArchived());
        return vehicleTypeRepository.saveVehicleType(oldVehicleType);
    }
}
