package basworld.backend.depot;

import basworld.backend.business.impl.depot.UpdateDepotUseCaseImpl;
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
public class UpdateDepotUseCaseImplTest {
    @Mock
    private DepotRepository depotRepository;

    @InjectMocks
    private UpdateDepotUseCaseImpl updateDepotUseCaseImpl;

    @Test
    public void testUpdateDepot_shouldReturnUpdatedDepot_whenDepotExists() {
        //arrange
        Depot depot = new Depot(1L, "Main Depot", "Netherlands", false);
        Depot newDepot = new Depot(1L, "Main Depot2.0", "Netherlands", false);
        when(depotRepository.findById(newDepot.getId())).thenReturn(Optional.of(depot));
        when(depotRepository.existsByNameAndArchivedFalse(newDepot.getDepotName())).thenReturn(false);
        when(depotRepository.saveDepot(depot)).thenReturn(depot);

        //act
        Depot result = updateDepotUseCaseImpl.updateDepot(newDepot);

        //assert
        assertEquals(newDepot.getDepotName(), result.getDepotName());
        assertEquals(newDepot.getLocation(), result.getLocation());
    }

    @Test
    public void testUpdateDepot_shouldThrowIllegalArgumentException_whenDepotDoesNotExist() {
        //arrange
        Depot newDepot = new Depot(1L, "Main Depot2.0", "Netherlands", false);
        when(depotRepository.findById(newDepot.getId())).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> updateDepotUseCaseImpl.updateDepot(newDepot));
    }

    @Test
    public void testUpdateDepot_shouldThrowIllegalArgumentException_whenDepotExists() {
        //arrange
        Depot depot = new Depot(1L, "Main Depot", "Netherlands", false);
        Depot newDepot = new Depot(1L, "Main Depot2.0", "Netherlands", false);
        when(depotRepository.findById(newDepot.getId())).thenReturn(Optional.of(depot));
        when(depotRepository.existsByNameAndArchivedFalse(newDepot.getDepotName())).thenReturn(true);

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> updateDepotUseCaseImpl.updateDepot(newDepot));
    }
}
