package basworld.backend.business.impl.vehicleType;

import basworld.backend.domain.repository.VehicleTypeRepository;
import basworld.backend.domain.vehicleType.VehicleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateVehicleTypeUseCaseImplTest {
    @Mock
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private CreateVehicleTypeUseCaseImpl createVehicleTypeUseCase;

    @Test
    public void createBrand_shouldCreateNewBrand_whenBrandDoesNotExist(){
        //arrange
        VehicleType vehicleType = new VehicleType(1L, "type", false);
        when(vehicleTypeRepository.existsByNameAndArchivedFalse(vehicleType.getName())).thenReturn(false);
        when(vehicleTypeRepository.saveVehicleType(vehicleType)).thenReturn(vehicleType);

        //act
        VehicleType savedVehicleType = createVehicleTypeUseCase.create(vehicleType);

        //assert
        assertEquals(vehicleType.getName(), savedVehicleType.getName());
        assertEquals(vehicleType.isArchived(), savedVehicleType.isArchived());
    }

    @Test
    public void createBrand_shouldThrowIllegalArgumentException_whenBrandExists(){
        //arrange
        VehicleType vehicleType = new VehicleType(1L, "type", true);
        when(vehicleTypeRepository.existsByNameAndArchivedFalse(vehicleType.getName())).thenReturn(true);

        //act and arrange
        assertThrows(IllegalArgumentException.class, () -> createVehicleTypeUseCase.create(vehicleType));
        verify(vehicleTypeRepository, never()).saveVehicleType(any(VehicleType.class));
    }
}