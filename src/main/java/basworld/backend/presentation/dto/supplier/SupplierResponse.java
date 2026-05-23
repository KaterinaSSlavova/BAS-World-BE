package basworld.backend.presentation.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SupplierResponse {
    Long id;
    String name;
    String picture;
    boolean archived;
}
