package basworld.backend.domain.depot;

import basworld.backend.domain.product.Product;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductDepot {
    private Product product;
    private Depot depot;
    private boolean isAvailable;
    private Long stockQuantity;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private Integer stockThreshold;

    @Builder
    public ProductDepot(Product product, Depot depot, boolean isAvailable, Long stockQuantity, BigDecimal costPrice, BigDecimal salePrice, Integer stockThreshold) {
        if(product == null) throw new IllegalArgumentException("Product cannot be null!");
        if(depot==null) throw new IllegalArgumentException("Depot cannot be null!");
        if(stockQuantity<0) throw new IllegalArgumentException("Stock quantity cannot be negative!");
        if(costPrice.compareTo(BigDecimal.ZERO) <= 0 ) throw new IllegalArgumentException("Cost price cannot be 0 or negative");
        if(salePrice.compareTo(BigDecimal.ZERO) <= 0 ) throw new IllegalArgumentException("Sale price cannot be 0 or negative");
        if(stockQuantity==0 && isAvailable) throw new IllegalArgumentException("Product cannot be available when stock is 0!");
        if(stockThreshold < 0) throw new IllegalArgumentException("Stock threshold must be greater than 0!");

        this.product = product;
        this.depot = depot;
        this.isAvailable = isAvailable;
        this.stockQuantity = stockQuantity;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.stockThreshold = stockThreshold;
    }
    public void update(boolean isAvailable, Long stockQuantity, BigDecimal costPrice, BigDecimal salePrice, Integer stockThreshold) {
        if(stockQuantity<0) throw new IllegalArgumentException("Stock quantity cannot be negative!");
        if(costPrice.compareTo(BigDecimal.ZERO) <= 0 ) throw new IllegalArgumentException("Cost price cannot be 0 or negative");
        if(salePrice.compareTo(BigDecimal.ZERO) <= 0 ) throw new IllegalArgumentException("Sale price cannot be 0 or negative");
        if(stockQuantity==0 && isAvailable) throw new IllegalArgumentException("Product cannot be available when stock is 0!");
        if(stockThreshold < 0) throw new IllegalArgumentException("Stock threshold must be greater than 0!");

        this.isAvailable = isAvailable;
        this.stockQuantity = stockQuantity;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.stockThreshold = stockThreshold;
    }
}
