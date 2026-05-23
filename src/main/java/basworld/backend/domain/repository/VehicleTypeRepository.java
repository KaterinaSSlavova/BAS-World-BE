package basworld.backend.domain.repository;

import basworld.backend.domain.vehicleType.VehicleType;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeRepository {
    Optional<VehicleType> findById(Long id);
    VehicleType saveVehicleType(VehicleType vehicleType);
    List<VehicleType> findAll();
    boolean existsByNameAndArchivedFalse(String name);
}
