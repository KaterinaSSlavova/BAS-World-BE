package basworld.backend.infrastructure.config.db.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Vehicle_Type")
public class VehicleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="vehicle_type_name")
    private String name;

    @Column(name="is_archived")
    private boolean archived;
}
