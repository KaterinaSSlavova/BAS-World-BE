package basworld.backend.business.impl.brand;

import basworld.backend.business.useCase.brand.UpdateBrandUseCase;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateBrandUseCaseImpl implements UpdateBrandUseCase {
    private final BrandRepository brandRepository;

    @Override
    public Brand updateBrand(Brand newBrand) {
        Brand oldBrand = brandRepository.findById(newBrand.getId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        
        if(!oldBrand.getName().equalsIgnoreCase(newBrand.getName())
                && brandRepository.existsByNameAndArchivedFalse(newBrand.getName())) {
            throw new IllegalArgumentException("This brand already exists!");
        }

        oldBrand.setName(newBrand.getName());

        if(newBrand.getPicture()!=null){
            oldBrand.setPicture(newBrand.getPicture());
        }

        return brandRepository.saveBrand(oldBrand);
    }
}
