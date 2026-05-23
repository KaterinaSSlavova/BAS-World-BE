package basworld.backend.domain.repository;

import basworld.backend.domain.supplier.Supplier;

import java.util.Optional;

public interface SupplierRepository {
    Optional<Supplier> findById(Long id);
}
