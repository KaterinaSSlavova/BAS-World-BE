package basworld.backend.domain.repository;

import basworld.backend.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    Optional<Supplier> findById(Long id);
    Supplier saveSupplier(Supplier supplier);
    List<Supplier> findAll();
    boolean existsByNameAndArchivedFalse(String name);
}
