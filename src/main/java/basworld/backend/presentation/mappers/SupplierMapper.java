package basworld.backend.presentation.mappers;

import basworld.backend.domain.supplier.Supplier;
import basworld.backend.presentation.dto.supplier.SupplierResponse;

public class SupplierMapper {
    public static SupplierResponse toSupplierResponse(Supplier supplier){
        return new SupplierResponse(
                supplier.getId(), supplier.getName(), supplier.getPicture(), supplier.isArchived()
        );
    }
}
