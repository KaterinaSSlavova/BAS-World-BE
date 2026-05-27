package basworld.backend.business.impl.supplier;

import basworld.backend.business.impl.supplier.UpdateSupplierUseCaseImpl;
import basworld.backend.domain.supplier.Supplier;
import basworld.backend.domain.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateSupplierUseCaseImplTest {
    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private UpdateSupplierUseCaseImpl updateSupplierUseCaseImpl;

    @Test
    public void updateSupplier_shouldReturnUpdatedSupplier_whenChangesAreValid(){
        //arrange
        Supplier supplier = new Supplier(1L, "Bosch", "bosch_logo.png", false);
        Supplier newSupplier = new Supplier(1L, "Bosch2", null, false);
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));
        when(supplierRepository.existsByNameAndArchivedFalse(newSupplier.getName())).thenReturn(false);
        when(supplierRepository.saveSupplier(supplier)).thenReturn(supplier);

        //act
        Supplier result = updateSupplierUseCaseImpl.updateSupplier(newSupplier, supplier.getId());

        //assert
        assertEquals(newSupplier.getName(), result.getName());
        assertEquals(supplier.getPicture(), result.getPicture());
    }

    @Test
    public void updateSupplier_shouldThrowIllegalArgumentException_whenSupplierDoesNotExist(){
        //arrange
        Supplier supplier = new Supplier(1L, "Bosch", "bosch_logo.png", false);
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> updateSupplierUseCaseImpl.updateSupplier(supplier, supplier.getId()));
        verify(supplierRepository, never()).saveSupplier(any(Supplier.class));
    }
}