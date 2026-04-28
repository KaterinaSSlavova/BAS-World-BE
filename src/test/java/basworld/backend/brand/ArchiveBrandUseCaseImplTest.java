package basworld.backend.brand;

import basworld.backend.business.impl.brand.ArchiveBrandUseCaseImpl;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArchiveBrandUseCaseImplTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private ArchiveBrandUseCaseImpl archiveBrandUseCaseImpl;

    @Test
    public void archiveBrand_shouldArchiveAirport_whenBrandExists(){
        //arrange
        Brand brand = new Brand(1L, "Michelin", "michelin_logo.png", false);
        when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));

        //act
        archiveBrandUseCaseImpl.archiveBrand(brand.getId());

        //assert
        assertTrue(brand.isArchived());
        verify(brandRepository).saveBrand(brand);
    }

    @Test
    public void archiveBrand_shouldThrowIllegalArgumentException_whenBrandDoesNotExist(){
        //arrange
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> archiveBrandUseCaseImpl.archiveBrand(1L));
        verify(brandRepository,never()).saveBrand(any(Brand.class));
    }
}
