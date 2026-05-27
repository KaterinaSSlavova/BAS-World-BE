package basworld.backend.business.impl.supplier;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import basworld.backend.domain.supplier.Supplier;
import basworld.backend.domain.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateSupplierUseCaseImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private CreateSupplierUseCaseImpl createSupplierUseCaseImpl;

    @Test
    public void createSupplier_shouldCreateNewSupplier_whenSupplierDoesNotExist(){
        //arrange
        Supplier supplier = new Supplier(1L, "Bosch", "bosch_logo.png", false);
        when(supplierRepository.existsByNameAndArchivedFalse(supplier.getName())).thenReturn(false);
        when(supplierRepository.saveSupplier(supplier)).thenReturn(supplier);

        //act
        Supplier savedSupplier = createSupplierUseCaseImpl.createSupplier(supplier);

        //assert
        assertEquals(supplier.getName(), savedSupplier.getName());
        assertEquals(supplier.getPicture(), savedSupplier.getPicture());
        assertEquals(supplier.isArchived(), savedSupplier.isArchived());
    }

    @Test
    public void createSupplier_shouldThrowIllegalArgumentException_whenSupplierExists(){
        //arrange
        Supplier supplier = new Supplier(1L, "Bosch", "bosch_logo.png", true);
        when(supplierRepository.existsByNameAndArchivedFalse(supplier.getName())).thenReturn(true);

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> createSupplierUseCaseImpl.createSupplier(supplier));
        verify(supplierRepository, never()).saveSupplier(any(Supplier.class));
    }
}