package basworld.backend.infrastructure.config.db.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String picture;

    @Column(name="supplier_name")
    private String name;

    @Column(name="is_archived")
    private boolean archived;
}
