package basworld.backend.infrastructure.config.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDepotId implements Serializable {

    @Column(name="product_id")
    private Long productId;

    @Column(name="depot_id")
    private Long depotId;
}
