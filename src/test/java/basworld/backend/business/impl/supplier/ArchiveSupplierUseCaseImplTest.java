package basworld.backend.business.impl.supplier;

import static org.junit.jupiter.api.Assertions.*;

import basworld.backend.domain.supplier.Supplier;
import basworld.backend.domain.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArchiveSupplierUseCaseImplTest {
    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private ArchiveSupplierUseCaseImpl archiveSupplierUseCaseImpl;

    @Test
    public void archiveSupplier_shouldArchiveSupplier_whenSupplierExists(){
        //arrange
        Supplier supplier = new Supplier(1L, "Bosch", "bosch_logo.png", false);
        when(supplierRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));

        //act
        archiveSupplierUseCaseImpl.archiveSupplier(supplier.getId());

        //assert
        assertTrue(supplier.isArchived());
        verify(supplierRepository).saveSupplier(supplier);
    }

    @Test
    public void archiveSupplier_shouldThrowIllegalArgumentException_whenSupplierDoesNotExist(){
        //arrange
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> archiveSupplierUseCaseImpl.archiveSupplier(1L));
        verify(supplierRepository, never()).saveSupplier(any(Supplier.class));
    }
}