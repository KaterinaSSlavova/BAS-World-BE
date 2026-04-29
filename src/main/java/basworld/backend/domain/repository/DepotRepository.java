package basworld.backend.domain.repository;

import basworld.backend.domain.depot.Depot;

import java.util.List;
import java.util.Optional;

public interface DepotRepository {
    Depot saveDepot(Depot depot);
    Optional<Depot> findById(Long id);
    boolean existsById(Long id);
    boolean existsByNameAndArchivedFalse(String name);
    List<Depot> findAll();
    List<Depot> findByMultipleIds(List<Long> ids);
}
