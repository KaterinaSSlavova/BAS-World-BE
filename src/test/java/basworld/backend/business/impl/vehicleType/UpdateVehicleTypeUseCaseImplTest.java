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
class UpdateVehicleTypeUseCaseImplTest {
    @Mock
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private UpdateVehicleTypeUseCaseImpl updateVehicleTypeUseCaseImpl;

    @Test
    public void updateVehicleType_shouldReturnUpdatedVehicleType_whenChangesAreValid(){
        //arrange
        VehicleType type = new VehicleType(1L, "Truck", false);
        VehicleType newType = new VehicleType(1L, "Heavy Truck", false);
        when(vehicleTypeRepository.findById(type.getId())).thenReturn(Optional.of(type));
        when(vehicleTypeRepository.existsByNameAndArchivedFalse(newType.getName())).thenReturn(false);
        when(vehicleTypeRepository.saveVehicleType(type)).thenReturn(type);

        //act
        VehicleType result = updateVehicleTypeUseCaseImpl.updateVehicleType(newType, type.getId());

        //assert
        assertEquals(newType.getName(), result.getName());
    }

    @Test
    public void updateVehicleType_shouldThrowIllegalArgumentException_whenTypeDoesNotExist(){
        //arrange
        VehicleType type = new VehicleType(1L, "Truck", false);
        when(vehicleTypeRepository.findById(type.getId())).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> updateVehicleTypeUseCaseImpl.updateVehicleType(type, type.getId()));
        verify(vehicleTypeRepository, never()).saveVehicleType(any(VehicleType.class));
    }
}