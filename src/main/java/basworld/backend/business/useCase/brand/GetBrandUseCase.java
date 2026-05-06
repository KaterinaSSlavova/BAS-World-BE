package basworld.backend.business.useCase.brand;

import basworld.backend.domain.brand.Brand;

public interface GetBrandUseCase {
    Brand getBrandById(Long id);
}
