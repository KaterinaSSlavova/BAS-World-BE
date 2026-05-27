package basworld.backend.business.impl.vehicleType;

import basworld.backend.domain.repository.VehicleTypeRepository;
import basworld.backend.domain.vehicleType.VehicleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArchiveVehicleTypeUseCaseImplTest {
    @Mock
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private ArchiveVehicleTypeUseCaseImpl archiveVehicleTypeUseCaseImpl;

    @Test
    public void archiveVehicleType_shouldArchiveVehicleType_whenTypeExists(){
        //arrange
        VehicleType type = new VehicleType(1L, "Truck", false);
        when(vehicleTypeRepository.findById(type.getId())).thenReturn(Optional.of(type));

        //act
        archiveVehicleTypeUseCaseImpl.archive(type.getId());

        //assert
        assertTrue(type.isArchived());
        verify(vehicleTypeRepository).saveVehicleType(type);
    }

    @Test
    public void archiveVehicleType_shouldThrowIllegalArgumentException_whenTypeDoesNotExist(){
        //arrange
        when(vehicleTypeRepository.findById(1L)).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> archiveVehicleTypeUseCaseImpl.archive(1L));
        verify(vehicleTypeRepository, never()).saveVehicleType(any(VehicleType.class));
    }
}