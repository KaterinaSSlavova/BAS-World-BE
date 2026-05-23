package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.repository.SupplierRepository;
import basworld.backend.domain.supplier.Supplier;
import basworld.backend.infrastructure.config.db.mappers.SupplierMapper;
import basworld.backend.infrastructure.config.db.repository.jpaSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository @RequiredArgsConstructor
public class SupplierRepositoryImpl implements SupplierRepository {
    private final jpaSupplierRepository jpaSupplierRepository;
    @Override
    public Optional<Supplier> findById(Long id) {
        return jpaSupplierRepository.findById(id).map(SupplierMapper::toDomain);
    }
}
