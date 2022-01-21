package cloud.officinaedisanto.heritage.objectspacemanagementservice.model;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.LocationReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class LocalizableEntity extends BaseEntity {

    private String entityId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LocationReference> locationReferences = new HashSet<>();

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Set<LocationReference> getLocationReferences() {
        return locationReferences;
    }

    public void setLocationReferences(Set<LocationReference> locationReferences) {
        this.locationReferences = locationReferences;
    }

}
