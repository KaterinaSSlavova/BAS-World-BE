package basworld.backend.domain.depot;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Depot {
    private Long id;
    private String depotName;
    private String location;

    @Builder
    public Depot(Long id, String depotName, String location) {
        if(depotName==null || depotName.isBlank()){
            throw new IllegalArgumentException("Depot name cannot be blank!");
        }

        if(location == null || location.isBlank()){
            throw new IllegalArgumentException("Depot location cannot be blank!");
        }

        this.id = id;
        this.depotName = depotName;
        this.location = location;
    }
}