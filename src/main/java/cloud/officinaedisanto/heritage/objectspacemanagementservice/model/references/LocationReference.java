package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BeaconReference.class, name = "beacon"),
        @JsonSubTypes.Type(value = CartesianReference.class, name = "cartesian"),
        @JsonSubTypes.Type(value = GeospatialReference.class, name = "geo"),
        @JsonSubTypes.Type(value = QRCodeReference.class, name = "qrcode")
})
@JsonTypeName("reference")
public abstract class LocationReference extends BaseEntity {
}
