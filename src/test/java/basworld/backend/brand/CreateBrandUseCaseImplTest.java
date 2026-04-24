package basworld.backend.brand;

import basworld.backend.business.impl.brand.CreateBrandUseCaseImpl;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateBrandUseCaseImplTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private CreateBrandUseCaseImpl createBrandUseCaseImpl;

    @Test
    public void createBrand_shouldCreateNewBrand_whenBrandDoesNotExist(){
        //arrange
        Brand brand = new Brand(1L, "Michelin", "michelin_logo.png", false);
        when(brandRepository.existsByNameAndArchivedFalse(brand.getName())).thenReturn(false);
        when(brandRepository.saveBrand(brand)).thenReturn(brand);

        //act
        Brand savedBrand = createBrandUseCaseImpl.createBrand(brand);

        //assert
        assertEquals(brand.getName(), savedBrand.getName());
        assertEquals(brand.getPicture(), savedBrand.getPicture());
        assertEquals(brand.isArchived(), savedBrand.isArchived());
    }

    @Test
    public void createBrand_shouldThrowIllegalArgumentException_whenBrandExists(){
        //arrange
        Brand brand = new Brand(1L, "Michelin", "michelin_logo.png", true);
        when(brandRepository.existsByNameAndArchivedFalse(brand.getName())).thenReturn(true);

        //act and arrange
        assertThrows(IllegalArgumentException.class, () -> createBrandUseCaseImpl.createBrand(brand));
        verify(brandRepository, never()).saveBrand(any(Brand.class));
    }
}
