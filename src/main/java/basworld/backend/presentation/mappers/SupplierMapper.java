package basworld.backend.presentation.mappers;

import basworld.backend.domain.supplier.Supplier;
import basworld.backend.presentation.dto.supplier.SupplierRequest;
import basworld.backend.presentation.dto.supplier.SupplierResponse;

public class SupplierMapper {
    public static SupplierResponse toSupplierResponse(Supplier supplier){
        return new SupplierResponse(
                supplier.getId(), supplier.getName(), supplier.getPicture(), supplier.isArchived()
        );
    }
    public static Supplier toSupplier(SupplierRequest request){
        return new Supplier(request.getName(), request.getPicture(), request.isArchived());
    }
}
