package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.type.Type;
import basworld.backend.infrastructure.config.db.entity.TypeEntity;

public class TypeMapper {
    public static TypeEntity toEntity(Type type) {
        if (type == null) return null;

        return TypeEntity.builder()
                .id(type.getId())
                .name(type.getName())
                .parent(type.getParent() != null
                        ? TypeEntity.builder()
                        .id(type.getParent().getId())
                        .build()
                        : null)
                .build();
    }
    public static Type fromEntity(TypeEntity entity) {
        if (entity.getId() == null) return null;
        return new Type(entity.getId(), entity.getName(), fromEntity(entity.getParent()));
    }
}
