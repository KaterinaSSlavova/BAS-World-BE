package basworld.backend.business.useCase.supplier;

import basworld.backend.domain.supplier.Supplier;

public interface GetSupplierByIdUseCase {
    Supplier getSupplierById(Long supplierId);
}
