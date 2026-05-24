package basworld.backend.business.impl.supplier;

import basworld.backend.business.useCase.supplier.GetSupplierByIdUseCase;
import basworld.backend.domain.repository.SupplierRepository;
import basworld.backend.domain.supplier.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class GetSupplierByIdUseCaseImpl implements GetSupplierByIdUseCase {
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier getSupplierById(Long supplierId) {
        if (supplierId <= 0) {
            throw new IllegalArgumentException("Supplier id must be greater than zero!");
        }
        return supplierRepository.findById(supplierId).orElseThrow(() -> new IllegalArgumentException("Supplier not found!"));
    }
}
