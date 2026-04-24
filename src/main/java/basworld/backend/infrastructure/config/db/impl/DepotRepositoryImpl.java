package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import basworld.backend.infrastructure.config.db.entity.DepotEntity;
import basworld.backend.infrastructure.config.db.mappers.DepotMapper;
import basworld.backend.infrastructure.config.db.repository.jpaDepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DepotRepositoryImpl implements DepotRepository {
    private final jpaDepotRepository jpaDepotRepository;

    @Override
    public Depot saveDepot(Depot depot) {
        DepotEntity entity = DepotEntity.builder()
                .id(depot.getId())
                .depotName(depot.getDepotName())
                .Location(depot.getLocation())
                .archived(depot.isArchived())
                .build();
        DepotEntity savedDepotEntity = jpaDepotRepository.save(entity);
        return DepotMapper.toDomain(savedDepotEntity);
    }

    @Override
    public Optional<Depot> findById(Long id) {
        return jpaDepotRepository.findById(id).filter(d -> !d.isArchived()).map(DepotMapper::toDomain);
    }

    @Override
    public List<Depot> findAll(){
        return jpaDepotRepository.findAll().stream().filter(d -> !d.isArchived()).map(DepotMapper::toDomain).toList();
    }

    @Override
    public boolean existsByNameAndArchivedFalse(String name){
        return jpaDepotRepository.existsByDepotNameAndArchivedFalse(name);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaDepotRepository.existsById(id);
    }
}
