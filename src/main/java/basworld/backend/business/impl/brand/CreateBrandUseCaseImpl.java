package basworld.backend.business.impl.brand;

import basworld.backend.business.useCase.brand.CreateBrandUseCase;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBrandUseCaseImpl implements CreateBrandUseCase {
    private final BrandRepository brandRepository;

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.saveBrand(brand);
    }
}
