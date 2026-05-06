package basworld.backend.business.useCase.brand;

import basworld.backend.domain.brand.Brand;

import java.util.List;

public interface GetAllBrandsUseCase {
    List<Brand> findAll();
}
