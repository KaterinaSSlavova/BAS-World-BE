package basworld.backend.depot;

import basworld.backend.business.impl.depot.ArchiveDepotUseCaseImpl;
import basworld.backend.domain.depot.Depot;
import basworld.backend.domain.repository.DepotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArchiveDepotUseCaseImplTest {
    @Mock
    private DepotRepository depotRepository;

    @InjectMocks
    private ArchiveDepotUseCaseImpl archiveDepotUseCaseImpl;

    @Test
    void archiveDepot_shouldArchiveDepot_whenDepotExists() {
        //arrange
        Depot depot = new Depot(1L, "Main Depot", "Netherlands", false);
        when(depotRepository.findById(depot.getId())).thenReturn(Optional.of(depot));
        when(depotRepository.saveDepot(depot)).thenReturn(depot);

        //act
        archiveDepotUseCaseImpl.archiveDepot(depot.getId());

        //assert
        assertTrue(depot.isArchived());
    }

    @Test
    void archiveDepot_shouldThrowIllegalArgumentException_whenDepotNotFound() {
        //arrange
        when(depotRepository.findById(1L)).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> archiveDepotUseCaseImpl.archiveDepot(1L));
        verify(depotRepository, never()).saveDepot(any(Depot.class));
    }
}
