package basworld.backend.domain.repository;

import basworld.backend.domain.brand.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> findById(Long id);
    Brand saveBrand(Brand brand);
    List<Brand> findAll();
    boolean existsByNameAndArchivedFalse(String name);
}
