package basworld.backend.infrastructure.config.db.mappers;

import basworld.backend.domain.brand.Brand;
import basworld.backend.infrastructure.config.db.entity.BrandEntity;

public class BrandMapper {
    public static BrandEntity toEntity(Brand brand){
        return BrandEntity.builder()
                .id(brand.getId())
                .brandName(brand.getName()).build();
    }
    public static Brand toDomain(BrandEntity brand){
        return new Brand(brand.getId(), brand.getBrandName());
    }
}
