package basworld.backend.business.impl.vehicleType;

import basworld.backend.business.useCase.vehicleType.ArchiveVehicleTypeUseCase;
import basworld.backend.domain.repository.VehicleTypeRepository;
import basworld.backend.domain.vehicleType.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ArchiveVehicleTypeUseCaseImpl implements ArchiveVehicleTypeUseCase {
    private final VehicleTypeRepository vehicleTypeRepository;
    @Override
    public void archive(Long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        vehicleType.setArchived(true);
        vehicleTypeRepository.saveVehicleType(vehicleType);
    }
}
