package basworld.backend.presentation.mappers;

import basworld.backend.domain.product.Product;
import basworld.backend.presentation.dto.product.ProductPublicData;

public class ProductDtoMapper {
    public static ProductPublicData toProductPublicData(Product product) {
        return new ProductPublicData(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                BrandMapper.toResponse(product.getBrand()),
                product.getStatus(),
                TypeDtoMapper.toTypePublicData(product.getType()),
                CategoryDtoMapper.toCategoryPublicData(product.getCategory()),
                VehicleTypeMapper.toVehicleTypeResponse(product.getVehicleType())
        );
    }
}