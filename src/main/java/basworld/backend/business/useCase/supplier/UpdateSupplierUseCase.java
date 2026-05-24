package basworld.backend.business.useCase.supplier;

import basworld.backend.domain.supplier.Supplier;

public interface UpdateSupplierUseCase {
    Supplier updateSupplier(Supplier supplier, Long supplierId);
}
