package basworld.backend.domain.repository;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;

import java.util.List;

public interface ProductDepotRepository {
    ProductDepot save(ProductDepot productDepot);
    boolean existsByProductIdAndDepotId(Long productId, Long depotId);
    List<ProductDepot> findAll();
    List<ProductDepot> search(String query);
    List<ProductDepot> saveAll(List<ProductDepot> productDepots);
    List<ProductDepot> findByProductId(Long productId);
    void deleteAll(List<ProductDepot> productDepots);
    List<ProductDepot> findByProductIn(List<Long> productIds);
}