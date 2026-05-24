package basworld.backend.business.impl.supplier;

import basworld.backend.business.useCase.supplier.ArchiveSupplierUseCase;
import basworld.backend.domain.repository.SupplierRepository;
import basworld.backend.domain.supplier.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ArchiveSupplierUseCaseImpl implements ArchiveSupplierUseCase {
    private final SupplierRepository supplierRepository;
    @Override
    public void archiveSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        supplier.setArchived(true);
        supplierRepository.saveSupplier(supplier);
    }
}
