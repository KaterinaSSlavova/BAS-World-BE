package basworld.backend.domain.depot;

import basworld.backend.domain.product.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDepot {
    private Product product;
    private Depot depot;
    private boolean isArchive;
    private Long stockQuantity;

    public ProductDepot(Product product, Depot depot, boolean isArchive, Long stockQuantity) {
        if(product == null) throw new IllegalArgumentException("Product cannot be null!");
        if(depot==null) throw new IllegalArgumentException("Depot cannot be null!");
        if(stockQuantity<=0) throw new IllegalArgumentException("Stock quantity cannot be negative!");

        this.product = product;
        this.depot = depot;
        this.isArchive = isArchive;
        this.stockQuantity = stockQuantity;
    }
}
