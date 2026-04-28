package basworld.backend.business.impl.brand;

import basworld.backend.business.useCase.brand.GetAllBrandsUseCase;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllBrandsUseCaseImpl implements GetAllBrandsUseCase {
    private final BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
