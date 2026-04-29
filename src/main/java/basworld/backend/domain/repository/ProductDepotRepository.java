package basworld.backend.domain.repository;

import basworld.backend.domain.depot.ProductDepot;

import java.util.List;

public interface ProductDepotRepository {
    ProductDepot save(ProductDepot productDepot);
    boolean existsByProductIdAndDepotId(Long productId, Long depotId);
    List<ProductDepot> findAll();
    List<ProductDepot> search(String query);
    List<ProductDepot> saveMultiple(List<ProductDepot> productDepots);
}