package basworld.backend.infrastructure.config.db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_type_id")
    private TypeEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<TypeEntity> children = new ArrayList<>();

}