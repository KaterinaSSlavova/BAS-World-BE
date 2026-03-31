package basworld.backend.infrastructure.config.db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Depot")
@Builder
public class DepotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="depot_name")
    private String depotName;

    @Column(name="location")
    private String Location;

    @OneToMany(mappedBy = "Depot")
    @Builder.Default
    private Set<ProductDepotEntity> productDepots = new HashSet<>();
}
