package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.LocationReference;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;
import com.blazebit.persistence.view.IdMapping;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@EntityView(LocationReference.class)
@EntityViewInheritance
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BeaconReferenceView.class, name = "beacon"),
        @JsonSubTypes.Type(value = CartesianReferenceView.class, name = "cartesian"),
        @JsonSubTypes.Type(value = GeospatialReferenceView.class, name = "geo"),
        @JsonSubTypes.Type(value = QRCodeReferenceView.class, name = "qrcode")
})
@JsonTypeName("reference")
public interface LocationReferenceView {

    @IdMapping
    Long getId();

}
