package basworld.backend.infrastructure.config.db.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Product_Depot")
public class ProductDepotEntity {

    @EmbeddedId
    private ProductDepotId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("depotId")
    @JoinColumn(name="depot_id")
    private DepotEntity depot;

    @Column(name="is_available")
    private Boolean isAvailable;

    @Column(name="stock_quantity")
    private Long stockQuantity;
}
