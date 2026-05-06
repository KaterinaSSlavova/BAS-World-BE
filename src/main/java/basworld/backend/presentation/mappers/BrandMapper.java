package basworld.backend.presentation.mappers;

import basworld.backend.domain.brand.Brand;
import basworld.backend.presentation.dto.brand.BrandResponse;
import basworld.backend.presentation.dto.brand.BrandRequest;

public class BrandMapper {
    public static BrandResponse toResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .picture(brand.getPicture())
                .build();
    }

    public static Brand toDomain(BrandRequest request){
        return Brand.builder()
                .picture(request.getPicture())
                .name(request.getName())
                .build();
    }
}
