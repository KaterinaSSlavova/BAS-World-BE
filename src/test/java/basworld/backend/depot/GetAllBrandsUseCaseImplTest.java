package basworld.backend.depot;

import basworld.backend.business.impl.depot.GetAllDepotsUseCaseImpl;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import basworld.backend.domain.repository.ProductInsightsRepository;
import basworld.backend.presentation.dto.depot.DepotOverviewDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllBrandsUseCaseImplTest {
    @Mock
    private DepotRepository depotRepository;

    @Mock
    private ProductInsightsRepository productInsightsRepository;

    @InjectMocks
    private GetAllDepotsUseCaseImpl getAllDepotsUseCaseImpl;

    @Test
    void getAllBrands_shouldReturnDepotsOverview_whenDepotsExist() {
        //arrange
        Depot depot1 = new Depot(1L, "Main Depot", "Netherlands", false);
        Depot depot2 = new Depot(2L, "Second Depot", "Belgium", false);
        when(depotRepository.findAll()).thenReturn(List.of(depot1, depot2));
        when(productInsightsRepository.countProductsByDepotId(depot1.getId())).thenReturn(5L);
        when(productInsightsRepository.countProductsByDepotId(depot2.getId())).thenReturn(3L);

        //act
        List<DepotOverviewDTO> result = getAllDepotsUseCaseImpl.getDepotOverview();

        //assert
        assertEquals(2, result.size());
        assertEquals("Main Depot", result.get(0).getDepotName());
        assertEquals("Netherlands", result.get(0).getLocation());
        assertEquals(5L, result.get(0).getNumberOfProducts());
        assertEquals("Second Depot", result.get(1).getDepotName());
        assertEquals("Belgium", result.get(1).getLocation());
        assertEquals(3L, result.get(1).getNumberOfProducts());
    }

    @Test
    void getAllBrands_shouldReturnEmptyList_whenNoDepotsExist() {
        //arrange
        when(depotRepository.findAll()).thenReturn(List.of());

        //act
        List<DepotOverviewDTO> result = getAllDepotsUseCaseImpl.getDepotOverview();

        //assert
        assertEquals(0, result.size());
        verify(productInsightsRepository, never()).countProductsByDepotId(any());
    }
}
