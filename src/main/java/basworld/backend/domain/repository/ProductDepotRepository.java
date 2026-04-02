package basworld.backend.domain.repository;

import basworld.backend.domain.depot.ProductDepot;

import java.util.List;

public interface ProductDepotRepository {
    List<ProductDepot> findAll();
}