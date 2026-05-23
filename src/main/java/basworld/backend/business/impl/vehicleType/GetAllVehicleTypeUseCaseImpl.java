package basworld.backend.business.impl.vehicleType;

import basworld.backend.business.useCase.vehicleType.GetAllVehicleTypeUseCase;
import basworld.backend.domain.repository.VehicleTypeRepository;
import basworld.backend.domain.vehicleType.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class GetAllVehicleTypeUseCaseImpl implements GetAllVehicleTypeUseCase {
    private VehicleTypeRepository vehicleTypeRepository;
    public List<VehicleType> getAllVehicleType(){
        return vehicleTypeRepository.findAll();
    }
}
