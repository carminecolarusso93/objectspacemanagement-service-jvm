package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.app;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.Space;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.DigitalModelView;
import com.blazebit.persistence.view.EntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Set;

@EntityView(Space.class)
@JsonTypeName("space")
public interface SpaceAppView extends LocalizableEntityAppView {

    Set<DigitalModelView> getDigitalModels();

}
