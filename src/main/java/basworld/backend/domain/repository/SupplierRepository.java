package basworld.backend.domain.repository;

import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.supplier.Supplier;
import basworld.backend.domain.vehicleType.VehicleType;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    Optional<Supplier> findById(Long id);
    Supplier saveSupplier(Supplier supplier);
    List<Supplier> findAll();
    boolean existsByNameAndArchivedFalse(String name);
}
