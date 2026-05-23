package basworld.backend.business.impl.supplier;

import basworld.backend.business.useCase.supplier.CreateSupplierUseCase;
import basworld.backend.domain.repository.SupplierRepository;
import basworld.backend.domain.supplier.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CreateSupplierUseCaseImpl implements CreateSupplierUseCase {
    private final SupplierRepository supplierRepository;
    public Supplier createSupplier(Supplier supplier) {
        if (supplierRepository.existsByNameAndArchivedFalse(supplier.getName())){
            throw new IllegalArgumentException("This supplier already exists!");
        }
        return supplierRepository.saveSupplier(supplier);
    }
}
