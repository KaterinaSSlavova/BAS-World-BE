package basworld.backend.domain.depot;

import basworld.backend.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockAlert {
    private ProductDepot productDepot;
    private StockAlertStatus status;
}