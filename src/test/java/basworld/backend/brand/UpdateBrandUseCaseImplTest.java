package basworld.backend.brand;

import basworld.backend.business.impl.brand.UpdateBrandUseCaseImpl;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateBrandUseCaseImplTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private UpdateBrandUseCaseImpl updateBrandUseCaseImpl;

    @Test
    public void updateBrand_shouldReturnUpdatedBrand_whenChangesAreValid(){
        //arrange
        Brand brand = new Brand(1L, "Michelin", "michelin_logo.png", false);
        Brand newBrand = new Brand(1L, "Michelin2", null, false);
        when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));
        when(brandRepository.existsByNameAndArchivedFalse(newBrand.getName())).thenReturn(false);
        when(brandRepository.saveBrand(brand)).thenReturn(brand);

        //act
        Brand result = updateBrandUseCaseImpl.updateBrand(newBrand);

        //assert
        assertEquals(newBrand.getName(),result.getName());
        assertEquals(brand.getPicture(),result.getPicture());
    }

    @Test
    public void updateBrand_shouldThrowIllegalArgumentException_whenBrandDoesNotExist(){
        //arrange
        Brand brand = new Brand(1L, "Michelin", "michelin_logo.png", false);
        when(brandRepository.findById(brand.getId())).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> updateBrandUseCaseImpl.updateBrand(brand));
        verify(brandRepository, never()).saveBrand(any(Brand.class));
    }
}
