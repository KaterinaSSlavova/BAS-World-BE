package basworld.backend.business.impl.supplier;

import basworld.backend.business.useCase.supplier.GetAllSupplierUseCase;
import basworld.backend.domain.repository.SupplierRepository;
import basworld.backend.domain.supplier.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class GetAllSupplierUseCaseImpl implements GetAllSupplierUseCase {
    private final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}
