package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.BeaconReference;
import com.blazebit.persistence.view.EntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;

@EntityView(BeaconReference.class)
@JsonTypeName("beacon")
public interface BeaconReferenceView extends LocationReferenceView {

    String getUuid();

    Integer getMajor();

    Integer getMinor();

}
