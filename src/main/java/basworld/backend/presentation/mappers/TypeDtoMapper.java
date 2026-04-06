package basworld.backend.presentation.mappers;


import basworld.backend.domain.type.Type;
import basworld.backend.presentation.dto.TypePublicData;

public class TypeDtoMapper {
    public static TypePublicData toTypePublicData(Type type) {
        if (type == null) { return null; }
        return new TypePublicData(type.getId(), type.getName(), TypeDtoMapper.toTypePublicData(type.getParent()));
    }
}
