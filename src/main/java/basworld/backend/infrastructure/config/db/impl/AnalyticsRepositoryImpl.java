package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.analytics.CategoryValue;
import basworld.backend.domain.analytics.DepotInventoryValue;
import basworld.backend.domain.analytics.DepotProductCount;
import basworld.backend.domain.product.Product;
import basworld.backend.domain.repository.AnalyticsRepository;
import basworld.backend.infrastructure.config.db.mappers.ProductMapper;
import basworld.backend.infrastructure.config.db.repository.AnalyticsJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository @RequiredArgsConstructor
public class AnalyticsRepositoryImpl implements AnalyticsRepository {
    private final AnalyticsJPARepository jpaRepository;

    @Override
    public List<CategoryValue> getStockValueByCategory() {
        return jpaRepository.findStockValueByCategory().stream()
                .map(p -> new CategoryValue(p.getCategoryId(), p.getCategoryName(), p.getTotalValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DepotProductCount> getProductCountByDepot() {
        return jpaRepository.countProductsByDepot().stream()
                .map(p -> new DepotProductCount(p.getDepotId(), p.getDepotName(), p.getTotalProducts()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DepotInventoryValue> getInventoryValueByDepot() {
        return jpaRepository.findInventoryValueByDepot().stream()
                .map(p -> new DepotInventoryValue(p.getDepotId(), p.getDepotName(), p.getTotalValue()))
                .collect(Collectors.toList());
    }

    @Override
    public Product getHighestQuantityProduct() {
        List<Product> products = jpaRepository.findTopProductByQuantity(PageRequest.of(0, 1)).stream().map(ProductMapper::toDomain).toList();
        return products.isEmpty() ? null : products.get(0);
    }
}
