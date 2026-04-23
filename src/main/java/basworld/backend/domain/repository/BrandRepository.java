package basworld.backend.domain.repository;

import basworld.backend.domain.brand.Brand;

public interface BrandRepository {
    Brand findById(Long id);
}
