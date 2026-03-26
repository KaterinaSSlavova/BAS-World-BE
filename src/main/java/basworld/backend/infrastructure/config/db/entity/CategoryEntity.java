package basworld.backend.infrastructure.config.db.entity;

import basworld.backend.domain.category.Category;
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
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<CategoryEntity> children = new ArrayList<>();

    public static CategoryEntity toEntity(Category category) {
        if (category == null) return null;
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .parent(category.getParent() != null
                        ? CategoryEntity.builder()
                        .id(category.getParent().getId())
                        .build()
                        : null)
                .build();
    }
    public Category fromEntity() {
        if (this.id == null) return null;
        return new Category(id, name, parent.fromEntity());
    }

}