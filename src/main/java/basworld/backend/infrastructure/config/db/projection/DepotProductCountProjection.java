package basworld.backend.infrastructure.config.db.projection;

public interface DepotProductCountProjection {
    Long getDepotId();
    String getDepotName();
    Long getTotalProducts();
}
