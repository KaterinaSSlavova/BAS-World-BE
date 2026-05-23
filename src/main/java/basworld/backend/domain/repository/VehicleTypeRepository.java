package basworld.backend.domain.repository;


import basworld.backend.domain.vehicleType.VehicleType;

import java.util.Optional;

public interface VehicleTypeRepository {
    Optional<VehicleType> findById(Long id);
}
