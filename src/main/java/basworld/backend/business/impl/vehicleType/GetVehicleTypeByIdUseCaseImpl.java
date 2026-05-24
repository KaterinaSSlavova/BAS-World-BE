package basworld.backend.business.impl.vehicleType;

import basworld.backend.business.useCase.vehicleType.GetVehicleTypeByIdUseCase;
import basworld.backend.domain.repository.VehicleTypeRepository;
import basworld.backend.domain.vehicleType.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class GetVehicleTypeByIdUseCaseImpl implements GetVehicleTypeByIdUseCase {
    private final VehicleTypeRepository vehicleTypeRepository;
    public VehicleType getVehicleTypeById(Long id) {
        if (id <= 0){
            throw new IllegalArgumentException("Invalid id!");
        }
        return vehicleTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("VehicleType not found!"));
    }
}
