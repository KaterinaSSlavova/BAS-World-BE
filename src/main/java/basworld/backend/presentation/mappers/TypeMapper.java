package basworld.backend.presentation.mappers;

import basworld.backend.domain.type.Type;
import basworld.backend.presentation.dto.type.TypeRequest;
import basworld.backend.presentation.dto.type.TypeResponse;

public class TypeMapper {

    public static TypeResponse toResponse(Type type) {
        return TypeResponse.builder()
                .id(type.getId())
                .name(type.getName())
                .isArchived(type.isArchived())
                .parentId(type.getParent() != null ? type.getParent().getId() : null)
                .parentName(type.getParent() != null ? type.getParent().getName() : null)
                .build();
    }

    public static Type toDomain(TypeRequest request) {
        return Type.builder()
                .name(request.getName())
                .isArchived(false)
                .parent(null)
                .build();
    }
}