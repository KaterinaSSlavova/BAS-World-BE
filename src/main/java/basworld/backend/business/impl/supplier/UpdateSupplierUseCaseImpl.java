package basworld.backend.business.impl.supplier;

import basworld.backend.business.useCase.supplier.UpdateSupplierUseCase;
import basworld.backend.domain.repository.SupplierRepository;
import basworld.backend.domain.supplier.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UpdateSupplierUseCaseImpl implements UpdateSupplierUseCase {
    private final SupplierRepository supplierRepository;
    @Override
    public Supplier updateSupplier(Supplier supplier, Long id){
        if (id <= 0){
            throw new IllegalArgumentException("Invalid id!");
        }
        var oldSupplier = supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Supplier not found!"));
        if(!oldSupplier.getName().equalsIgnoreCase(supplier.getName())
                && supplierRepository.existsByNameAndArchivedFalse(supplier.getName())) {
            throw new IllegalArgumentException("This supplier already exists!");
        }
        oldSupplier.update(supplier.getName(), supplier.getPicture(), supplier.isArchived());
        return supplierRepository.saveSupplier(oldSupplier);
    }
}
