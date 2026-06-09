package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.repository.ProductDepotRepository;
import basworld.backend.infrastructure.config.db.entity.ProductDepotEntity;
import basworld.backend.infrastructure.config.db.mappers.ProductDepotMapper;
import basworld.backend.infrastructure.config.db.repository.ProductDepotJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDepotRepositoryImpl implements ProductDepotRepository {
    private final ProductDepotJPARepository productDepotJpaRepository;

    @Override
    public ProductDepot save(ProductDepot productDepot) {
        ProductDepotEntity savedEntity = productDepotJpaRepository
                .save(ProductDepotMapper.toEntity(productDepot));
        return ProductDepotMapper.toDomain(savedEntity);
    }

    @Override
    public List<ProductDepot> findAll() {
        return productDepotJpaRepository.findAll()
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
    @Override
    public List<ProductDepot> saveAll(List<ProductDepot> productDepots) {
        return productDepotJpaRepository.saveAll(productDepots.stream().map(ProductDepotMapper::toEntity).toList())
                .stream()
                .map(ProductDepotMapper::toDomain)
                .toList();
    }
    @Override
    public List<ProductDepot> findByProductId(Long productId) {
        return productDepotJpaRepository.findAllByProductId(productId)
                .stream()
                .map(ProductDepotMapper::toDomain)
                .toList();
    }
    @Override
    public void deleteAll(List<ProductDepot> productDepots) {
        productDepotJpaRepository.deleteAll(productDepots.stream().map(ProductDepotMapper::toEntity).toList());
    }
    @Override
    public List<ProductDepot> findByProductIn(List<Long> productIds){
        return productDepotJpaRepository.findByProductIdIn(productIds).stream().map(ProductDepotMapper::toDomain).toList();
    }

    @Override
    public List<ProductDepot> findAllWithLowStock(){
        return productDepotJpaRepository.findAllWithLowStock().stream().map(ProductDepotMapper::toDomain).toList().reversed();
    }
}