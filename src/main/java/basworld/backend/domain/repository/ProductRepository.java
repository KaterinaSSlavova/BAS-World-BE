package basworld.backend.domain.repository;

import basworld.backend.domain.product.Product;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    boolean existsById(Long id);
    Optional<Product> findById(Long id);
}
