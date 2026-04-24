package basworld.backend.brand;

import basworld.backend.business.impl.brand.GetBrandUseCaseImpl;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBrandUseCaseImplTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private GetBrandUseCaseImpl getBrandUseCaseImpl;

    @Test
    public void getBrandById_shouldReturnBrand_whenBrandExists(){
        //arrange
        Brand brand = new Brand(1L, "Michelin", "michelin_logo.png", false);
        when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));

        //act
        Brand result = getBrandUseCaseImpl.getBrandById(brand.getId());

        //assert
        assertNotNull(result);
        assertEquals(brand.getId(), result.getId());
        verify(brandRepository).findById(brand.getId());
    }

    @Test
    public void getBrandById_shouldThrowIllegalArgumentException_whenBrandDoesNotExists(){
        //arrange
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> getBrandUseCaseImpl.getBrandById(1L));
    }
}
