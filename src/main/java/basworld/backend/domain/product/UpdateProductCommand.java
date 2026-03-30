package basworld.backend.domain.product;

import java.math.BigDecimal;

public record UpdateProductCommand(
        String name,
        String description,
        String brand,
        BigDecimal price,
        ProductStatus status,
        Long typeId,
        Long categoryId
) {}