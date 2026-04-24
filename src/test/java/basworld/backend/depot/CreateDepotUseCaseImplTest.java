package basworld.backend.depot;

import basworld.backend.business.impl.depot.CreateDepotUseCaseImpl;
import basworld.backend.domain.brand.Brand;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateDepotUseCaseImplTest {
    @Mock
    private DepotRepository depotRepository;

    @InjectMocks
    private CreateDepotUseCaseImpl createDepotUseCaseImpl;

    @Test
    public void createDepot_shouldReturnNewDepot_whenDepotWithTheSameNameDoesNotExist() {
        //arrange
        Depot depot = new Depot(1L, "Main Depot", "Netherlands", false);
        when(depotRepository.existsByNameAndArchivedFalse(depot.getDepotName())).thenReturn(false);
        when(depotRepository.saveDepot(depot)).thenReturn(depot);

        //act
        Depot result = createDepotUseCaseImpl.createDepot(depot);

        //assert
        assertEquals(depot.getDepotName(), result.getDepotName());
        assertEquals(depot.getLocation(), result.getLocation());
    }

    @Test
    public void createDepot_shouldThrowIllegalArgumentException_whenDepotWithTheSameNameExists() {
        //arrange
        Depot depot = new Depot(1L, "Main Depot", "Netherlands", false);
        when(depotRepository.existsByNameAndArchivedFalse(depot.getDepotName())).thenReturn(true);

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> createDepotUseCaseImpl.createDepot(depot));
        verify(depotRepository, never()).saveDepot(any(Depot.class));
    }
}