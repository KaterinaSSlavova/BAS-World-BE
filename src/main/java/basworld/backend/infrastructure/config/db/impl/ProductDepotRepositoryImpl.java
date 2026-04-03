package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.repository.ProductDepotRepository;
import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import basworld.backend.infrastructure.config.db.mappers.ProductDepotMapper;
import basworld.backend.infrastructure.config.db.repository.ProductDepotJPARepository;
import basworld.backend.infrastructure.config.db.repository.jpaProductDepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDepotRepositoryImpl implements ProductDepotRepository {

    private final jpaProductDepotRepository jpaProductDepotRepository;
    private final ProductDepotJPARepository productDepotJpaRepository;


    @Override
    public ProductDepot save(ProductDepot productDepot) {
        ProductDepotEntity savedEntity = jpaProductDepotRepository
                .save(ProductDepotMapper.toEntity(productDepot));
        return ProductDepotMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByProductIdAndDepotId(Long productId, Long depotId) {
        return jpaProductDepotRepository.existsByProductIdAndDepotId(productId, depotId);
    }

    @Override
    public List<ProductDepot> findAll() {
        return productDepotJpaRepository.findAllWithProductAndDepot()
                .stream()
                .map(ProductDepotMapper::toDomain)
                .toList();
    }

    @Override
    public List<ProductDepot> search(String query) {
        return productDepotJpaRepository.search(query)
                .stream()
                .map(ProductDepotMapper::toDomain)
                .toList();
    }
}