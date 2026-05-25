package basworld.backend.domain.repository;

import basworld.backend.domain.supplier.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository {
    Optional<Supplier> findById(Long id);
    Supplier saveSupplier(Supplier supplier);
    List<Supplier> findAll();
    boolean existsByNameAndArchivedFalse(String name);
}
