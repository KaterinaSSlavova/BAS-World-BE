package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.supplier.Supplier;
import basworld.backend.infrastructure.config.db.entity.SupplierEntity;

public class SupplierMapper {
    public static SupplierEntity toEntity(Supplier supplier) {
        return SupplierEntity.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .picture(supplier.getPicture())
                .archived(supplier.isArchived())
                .build();
    }

    public static Supplier toDomain(SupplierEntity entity) {
        return new Supplier
                (entity.getId(), entity.getName(), entity.getPicture(), entity.isArchived());
    }
}
