package basworld.backend.infrastructure.config.db.implementation;

import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.ProductRepository;
import basworld.backend.infrastructure.config.db.mappers.ProductMapper;
import basworld.backend.infrastructure.config.db.repository.jpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository @RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final jpaProductRepository jpaProductRepository;
    public Optional<Product> findById(Long id){
        return jpaProductRepository.findById(id).map(ProductMapper::fromEntity);
    }
    public Optional<Product> save(Product product){
        var productEntity = jpaProductRepository.save(ProductMapper.toEntity(product));
        return Optional.of(ProductMapper.fromEntity(productEntity));
    }
}
