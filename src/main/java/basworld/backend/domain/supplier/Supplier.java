package basworld.backend.domain.supplier;

import lombok.Getter;

@Getter
public class Supplier {
    private Long id;
    private String name;
    private String picture;
    private boolean isArchived;

    public Supplier(Long id, String name, String picture, boolean isArchived) {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.isArchived = isArchived;
    }
    public Supplier(String name, String picture, boolean isArchived) {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.picture = picture;
        this.isArchived = isArchived;
    }
    public Supplier(String name, String picture) {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.picture = picture;
        this.isArchived = false;
    }
    public void update(String newName,  String newPicture,boolean isArchived) {
        if (newName.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = newName;
        this.picture = newPicture;
        this.isArchived = isArchived;
    }
}
