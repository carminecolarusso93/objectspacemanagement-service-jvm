package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.BeaconReference;
import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;

@EntityView(BeaconReference.class)
@CreatableEntityView
@UpdatableEntityView
@JsonTypeName("beacon")
public interface BeaconReferenceUpdateView extends LocationReferenceUpdateView {

    String getUuid();

    void setUuid(String uuid);

    Integer getMajor();

    void setMajor(Integer major);

    Integer getMinor();

    void setMinor(Integer minor);

}
