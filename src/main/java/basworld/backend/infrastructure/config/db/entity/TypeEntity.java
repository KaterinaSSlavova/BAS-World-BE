package basworld.backend.infrastructure.config.db.entity;

import basworld.backend.domain.type.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
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

    public static TypeEntity toEntity(Type type) {
        if (type == null) return null;

        return TypeEntity.builder()
                .id(type.getId())
                .name(type.getName())
                .parent(type.getParent() != null
                        ? TypeEntity.builder()
                        .id(type.getParent().getId())
                        .build()
                        : null)
                .build();
    }
    public Type fromEntity() {
        if (this.id == null) return null;
        return new Type(id, name, parent.fromEntity());
    }

}