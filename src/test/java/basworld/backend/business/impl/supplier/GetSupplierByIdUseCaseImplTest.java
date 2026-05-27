package basworld.backend.business.impl.supplier;
import basworld.backend.business.impl.supplier.GetSupplierByIdUseCaseImpl;
import basworld.backend.domain.supplier.Supplier;
import basworld.backend.domain.repository.SupplierRepository;
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
public class GetSupplierByIdUseCaseImplTest {
    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private GetSupplierByIdUseCaseImpl getSupplierUseCaseImpl;

    @Test
    public void getSupplierById_shouldReturnSupplier_whenSupplierExists(){
        //arrange
        Supplier supplier = new Supplier(1L, "Bosch", "bosch_logo.png", false);
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));

        //act
        Supplier result = getSupplierUseCaseImpl.getSupplierById(supplier.getId());

        //assert
        assertNotNull(result);
        assertEquals(supplier.getId(), result.getId());
        verify(supplierRepository).findById(supplier.getId());
    }

    @Test
    public void getSupplierById_shouldThrowIllegalArgumentException_whenSupplierDoesNotExist(){
        //arrange
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> getSupplierUseCaseImpl.getSupplierById(1L));
    }
}