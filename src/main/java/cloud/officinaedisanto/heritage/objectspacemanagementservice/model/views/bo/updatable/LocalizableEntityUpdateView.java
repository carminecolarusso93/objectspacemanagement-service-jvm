package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.LocalizableEntity;
import com.blazebit.persistence.view.*;

@EntityView(LocalizableEntity.class)
@EntityViewInheritance
@CreatableEntityView
@UpdatableEntityView
public interface LocalizableEntityUpdateView {

    @IdMapping
    Long getId();

    String getEntityId();

    void setEntityId(String entityId);

}
