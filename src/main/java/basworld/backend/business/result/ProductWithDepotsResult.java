package basworld.backend.business.result;

import basworld.backend.domain.depot.ProductDepot;
import basworld.backend.domain.product.Product;

import java.util.List;

public record ProductWithDepotsResult (
    Product product,
    List<ProductDepot> depots
)
{}
