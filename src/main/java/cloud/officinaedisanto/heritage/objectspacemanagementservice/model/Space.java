package cloud.officinaedisanto.heritage.objectspacemanagementservice.model;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Space extends LocalizableEntity {

    @OneToMany
    @JoinTable(name = "space_spaces")
    private Set<Space> containedSpaces = new HashSet<>();

    @OneToMany
    @JoinTable(name = "space_entities")
    private Set<LocalizableEntity> hostedEntities = new HashSet<>();

    @OneToMany
    @JoinTable(name = "space_digitalmodels")
    private Set<DigitalModel> digitalModels;

    public Set<Space> getContainedSpaces() {
        return containedSpaces;
    }

    public void setContainedSpaces(Set<Space> containedSpaces) {
        this.containedSpaces = containedSpaces;
    }

    public Set<LocalizableEntity> getHostedEntities() {
        return hostedEntities;
    }

    public void setHostedEntities(Set<LocalizableEntity> hostedEntities) {
        this.hostedEntities = hostedEntities;
    }

    public Set<DigitalModel> getDigitalModels() {
        return digitalModels;
    }

    public void setDigitalModels(Set<DigitalModel> digitalModels) {
        this.digitalModels = digitalModels;
    }

}
