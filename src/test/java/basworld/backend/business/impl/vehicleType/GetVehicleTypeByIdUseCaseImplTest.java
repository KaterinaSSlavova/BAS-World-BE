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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVehicleTypeByIdUseCaseImplTest {
    @Mock
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private GetVehicleTypeByIdUseCaseImpl getVehicleTypeUseCaseImpl;

    @Test
    public void getVehicleTypeById_shouldReturnVehicleType_whenTypeExists(){
        //arrange
        VehicleType type = new VehicleType(1L, "Truck", false);
        when(vehicleTypeRepository.findById(type.getId())).thenReturn(Optional.of(type));

        //act
        VehicleType result = getVehicleTypeUseCaseImpl.getVehicleTypeById(type.getId());

        //assert
        assertNotNull(result);
        assertEquals(type.getId(), result.getId());
        verify(vehicleTypeRepository).findById(type.getId());
    }

    @Test
    public void getVehicleTypeById_shouldThrowIllegalArgumentException_whenTypeDoesNotExist(){
        //arrange
        when(vehicleTypeRepository.findById(1L)).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> getVehicleTypeUseCaseImpl.getVehicleTypeById(1L));
    }

}