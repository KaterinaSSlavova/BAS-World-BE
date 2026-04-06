package basworld.backend.domain.repository;

import basworld.backend.domain.product.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
    Optional<Product> save(Product product);
}
