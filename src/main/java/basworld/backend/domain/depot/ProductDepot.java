package basworld.backend.domain.depot;

import basworld.backend.domain.product.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDepot {
    private Product product;
    private Depot depot;
    private boolean isAvailable;
    private Long stockQuantity;

    @Builder
    public ProductDepot(Product product, Depot depot, boolean isAvailable, Long stockQuantity) {
        if(product == null) throw new IllegalArgumentException("Product cannot be null!");
        if(depot==null) throw new IllegalArgumentException("Depot cannot be null!");
        if(stockQuantity<=0) throw new IllegalArgumentException("Stock quantity cannot be negative!");

        this.product = product;
        this.depot = depot;
        this.isAvailable = isAvailable;
        this.stockQuantity = stockQuantity;
    }
}
