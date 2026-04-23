package basworld.backend.domain.depot;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Depot {
    @Setter
    private Long id;

    @Setter
    private String depotName;

    @Setter
    private String location;
    private boolean archived;

    @Builder
    public Depot(Long id, String depotName, String location, boolean archived) {
        if(depotName==null || depotName.isBlank()){
            throw new IllegalArgumentException("Depot name cannot be blank!");
        }

        if(location == null || location.isBlank()){
            throw new IllegalArgumentException("Depot location cannot be blank!");
        }

        this.id = id;
        this.depotName = depotName;
        this.location = location;
        this.archived = archived;
    }

    public void archiveDepot() {
        this.archived = true;
    }
}