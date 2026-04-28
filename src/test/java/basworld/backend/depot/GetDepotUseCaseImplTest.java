package basworld.backend.depot;

import basworld.backend.business.impl.depot.GetDepotUseCaseImpl;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetDepotUseCaseImplTest {
    @Mock
    private DepotRepository depotRepository;

    @InjectMocks
    private GetDepotUseCaseImpl getDepotUseCaseImpl;

    @Test
    void getDepot_shouldReturnDepot_whenDepotExists() {
        //arrange
        Depot depot = new Depot(1L, "Main Depot", "Netherlands", false);
        when(depotRepository.findById(depot.getId())).thenReturn(Optional.of(depot));

        //act
        Depot result = getDepotUseCaseImpl.getDepotById(depot.getId());

        //assert
        assertNotNull(result);
        assertEquals(depot.getId(), result.getId());
    }

    @Test
    void getDepot_shouldThrowIllegalArgumentException_whenDepotDoesNotExist() {
        //arrange
        Depot depot = new Depot(1L, "Main Depot", "Netherlands", false);
        when(depotRepository.findById(depot.getId())).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> getDepotUseCaseImpl.getDepotById(depot.getId()));
    }
}
