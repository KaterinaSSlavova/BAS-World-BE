package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.type.Type;
import basworld.backend.infrastructure.config.db.entity.TypeEntity;

public class TypeMapper {

    public static Type toDomain(TypeEntity entity) {
        if (entity == null) {
            return null;
        }

        Type parent = null;

        if (entity.getParent() != null) {
            parent = Type.builder()
                    .id(entity.getParent().getId())
                    .name(entity.getParent().getName())
                    .isArchived(entity.getParent().getIsArchived())
                    .parent(null)
                    .build();
        }

        return Type.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isArchived(entity.getIsArchived())
                .parent(parent)
                .build();
    }

    public static TypeEntity toEntity(Type type) {
        if (type == null) {
            return null;
        }

        TypeEntity parent = null;

        if (type.getParent() != null) {
            parent = TypeEntity.builder()
                    .id(type.getParent().getId())
                    .name(type.getParent().getName())
                    .isArchived(type.getParent().isArchived())
                    .build();
        }

        return TypeEntity.builder()
                .id(type.getId())
                .name(type.getName())
                .isArchived(type.isArchived())
                .parent(parent)
                .build();
    }
}