package basworld.backend.presentation.mappers;

import basworld.backend.domain.product.Product;
import basworld.backend.infrastructure.config.db.mappers.CategoryMapper;
import basworld.backend.infrastructure.config.db.mappers.TypeMapper;
import basworld.backend.presentation.dto.CategoryPublicData;
import basworld.backend.presentation.dto.ProductPublicData;

public class ProductDtoMapper {
    public static ProductPublicData toProductPublicData(Product product) {
        return new ProductPublicData(product.getId(), product.getSku(),product.getName(), product.getDescription(), product.getBrand(), product.getPrice(),
                product.getStatus(), TypeDtoMapper.toTypePublicData(product.getType()), CategoryDtoMapper.toCategoryPublicData(product.getCategory()));
    }
}
