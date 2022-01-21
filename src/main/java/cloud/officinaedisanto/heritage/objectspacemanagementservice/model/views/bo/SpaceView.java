package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.Space;
import com.blazebit.persistence.view.EntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;

@EntityView(Space.class)
@JsonTypeName("space")
public interface SpaceView extends LocalizableEntityView {

}
