package basworld.backend.infrastructure.config.db.impl;

import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import basworld.backend.infrastructure.config.db.entity.BrandEntity;
import basworld.backend.infrastructure.config.db.mappers.BrandMapper;
import basworld.backend.infrastructure.config.db.repository.jpaBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BrandRepositoryImpl implements BrandRepository {
    private final jpaBrandRepository jpaBrandRepository;

    @Override
    public Optional<Brand> findById(Long id) {
        return jpaBrandRepository.findById(id).filter(b -> !b.isArchived()).map(BrandMapper::toDomain);
    }

    @Override
    public Brand saveBrand(Brand brand) {
        BrandEntity entity = BrandMapper.toEntity(brand);
        return BrandMapper.toDomain(jpaBrandRepository.save(entity));
    }

    @Override
    public List<Brand> findAll() {
        return jpaBrandRepository.findAll().stream().filter(b -> !b.isArchived()).map(BrandMapper::toDomain).toList();
    }

    @Override
    public boolean existsByNameAndArchivedFalse(String name) {
        return jpaBrandRepository.existsByBrandNameAndArchivedFalse(name);
    }

}
