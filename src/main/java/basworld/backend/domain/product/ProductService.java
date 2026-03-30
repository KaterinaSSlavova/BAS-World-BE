package basworld.backend.domain.product;

import basworld.backend.domain.category.Category;
import basworld.backend.domain.type.Type;
import basworld.backend.infrastructure.config.db.entity.CategoryEntity;
import basworld.backend.infrastructure.config.db.entity.ProductEntity;
import basworld.backend.infrastructure.config.db.entity.TypeEntity;
import basworld.backend.infrastructure.config.db.repository.CategoryRepository;
import basworld.backend.infrastructure.config.db.repository.ProductRepository;
import basworld.backend.infrastructure.config.db.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;

    public Product updateProduct(Long id, UpdateProductCommand cmd) {
        // 1. Check product exists
        Product existing = productRepository.findById(id)
                .map(ProductEntity::toProduct)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));

        // 2. Resolve Type and Category
        Type type = typeRepository.findById(cmd.typeId())
                .map(TypeEntity::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Type not found: " + cmd.typeId()));

        Category category = categoryRepository.findById(cmd.categoryId())
                .map(CategoryEntity::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + cmd.categoryId()));

        // 3. Build updated product (keeps id and sku from existing)
        Product updated = new Product(
                existing.getId(),
                existing.getSku(),
                cmd.name(),
                cmd.description(),
                cmd.brand(),
                cmd.price(),
                cmd.status(),
                type,
                category
        );

        // 4. Save and return
        ProductEntity savedEntity = productRepository.save(ProductEntity.toEntity(updated));
        return savedEntity.toProduct();
    }
}