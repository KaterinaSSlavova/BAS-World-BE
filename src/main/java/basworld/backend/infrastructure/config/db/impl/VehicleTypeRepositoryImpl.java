package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.repository.VehicleTypeRepository;
import basworld.backend.domain.vehicleType.VehicleType;
import basworld.backend.infrastructure.config.db.mappers.VehicleTypeMapper;
import basworld.backend.infrastructure.config.db.repository.jpaVehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VehicleTypeRepositoryImpl implements VehicleTypeRepository {
    private final jpaVehicleTypeRepository jpaRepository;
    @Override
    public Optional<VehicleType> findById(Long id) {
        return jpaRepository.findById(id).map(VehicleTypeMapper::toDomain);
    }
}