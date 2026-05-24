package basworld.backend.domain.vehicleType;

import lombok.Getter;
import lombok.Setter;

@Getter
public class VehicleType {
    private Long id;
    private String name;
    @Setter
    private boolean isArchived;
    public VehicleType(Long id, String name, boolean isArchived) {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.id = id;
        this.name = name;
        this.isArchived = isArchived;
    }
    public VehicleType(String name, boolean archived) {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.isArchived = archived;
    }
    public void update(String name, boolean isArchived) {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.isArchived = isArchived;
    }
}
