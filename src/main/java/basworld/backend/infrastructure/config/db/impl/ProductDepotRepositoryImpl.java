package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.repository.ProductDepotRepository;
import basworld.backend.infrastructure.config.db.mappers.ProductDepotMapper;
import basworld.backend.infrastructure.config.db.repository.ProductDepotJPARepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDepotRepositoryImpl implements ProductDepotRepository {

    private final ProductDepotJPARepository productDepotJpaRepository;

    public ProductDepotRepositoryImpl(ProductDepotJPARepository productDepotJpaRepository) {
        this.productDepotJpaRepository = productDepotJpaRepository;
    }

    @Override
    public List<ProductDepot> findAll() {
        return productDepotJpaRepository.findAllWithProductAndDepot()
                .stream()
                .map(ProductDepotMapper::toDomain)
                .toList();
    }
}