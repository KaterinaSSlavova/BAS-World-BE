package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductRepository;
import basworld.backend.infrastructure.config.db.entity.ProductEntity;
import basworld.backend.infrastructure.config.db.mappers.ProductMapper;
import basworld.backend.infrastructure.config.db.repository.jpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final jpaProductRepository productRepository;

    @Override
    public Product save(Product product) {
        ProductEntity entity = productRepository.save(ProductMapper.toEntity(product));
        return ProductMapper.toDomain(entity);
    }

    @Override
    public Optional<Product> findById(Long id){
        return productRepository.findById(id).map(ProductMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<Product> findAll() { return productRepository.findAll().stream().map(ProductMapper::toDomain).toList();}
}
