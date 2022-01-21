package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.DigitalModel;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

@EntityView(DigitalModel.class)
public interface DigitalModelView {

    @IdMapping
    Long getId();

    Long getDigitalModelId();

    String getTag();

}
