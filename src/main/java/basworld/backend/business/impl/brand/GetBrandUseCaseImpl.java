package basworld.backend.business.impl.brand;

import basworld.backend.business.useCase.brand.GetBrandUseCase;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBrandUseCaseImpl implements GetBrandUseCase {
    private final BrandRepository brandRepository;

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new IllegalStateException("Brand not found!"));
    }
}
