package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.repository.ProductInsightsRepository;
import basworld.backend.infrastructure.config.db.repository.ProductInsightsJPARepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductInsightsRepositoryImpl implements ProductInsightsRepository {
    private final ProductInsightsJPARepository jpaRepository;


    public ProductInsightsRepositoryImpl(ProductInsightsJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public long countProductsByDepotId(Long depotId) {
        return jpaRepository.countProductsByDepotId(depotId);
    }

    @Override
    public long countLowStockProductsByDepotId(Long depotId) {
        return jpaRepository.countLowStockProductsByDepotId(depotId);
    }

    @Override
    public long countUnavailableItemsByDepotId(Long depotId) {
        return jpaRepository.countUnavailableItemsByDepotId(depotId);
    }

    @Override
    public BigDecimal sumInventoryValueByDepotId(Long depotId) {
        return jpaRepository.sumInventoryValueByDepotId(depotId);
    }

    @Override
    public long countProductsOverall() {
        return jpaRepository.countProductsOverall();
    }

    @Override
    public long countLowStockProductsOverall() {
        return jpaRepository.countLowStockProductsOverall();
    }

    @Override
    public long countUnavailableItemsOverall() {
        return jpaRepository.countUnavailableItemsOverall();
    }

    @Override
    public BigDecimal sumInventoryValueOverall() {
        return jpaRepository.sumInventoryValueOverall();
    }
}
