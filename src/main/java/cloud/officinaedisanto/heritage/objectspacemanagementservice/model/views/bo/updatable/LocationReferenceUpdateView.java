package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.LocationReference;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@EntityView(LocationReference.class)
@EntityViewInheritance
@UpdatableEntityView
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BeaconReferenceUpdateView.class, name = "beacon"),
        @JsonSubTypes.Type(value = CartesianReferenceUpdateView.class, name = "cartesian"),
        @JsonSubTypes.Type(value = GeospatialReferenceUpdateView.class, name = "geo"),
        @JsonSubTypes.Type(value = QRCodeReferenceUpdateView.class, name = "qrcode")
})
@JsonTypeName("reference")
public interface LocationReferenceUpdateView {

    @IdMapping
    Long getId();

}
