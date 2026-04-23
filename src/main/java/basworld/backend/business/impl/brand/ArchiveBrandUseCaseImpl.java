package basworld.backend.business.impl.brand;

import basworld.backend.business.useCase.brand.ArchiveBrandUseCase;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchiveBrandUseCaseImpl implements ArchiveBrandUseCase {
    private final BrandRepository brandRepository;

    @Override
    public void archiveBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        brand.setArchived(true);
        brandRepository.saveBrand(brand);
    }
}
