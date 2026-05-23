package basworld.backend.business.useCase.supplier;

import basworld.backend.domain.supplier.Supplier;

import java.util.List;

public interface GetAllSupplierUseCase {
    List<Supplier> getAllSuppliers();
}
