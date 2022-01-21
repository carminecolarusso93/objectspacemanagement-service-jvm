package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.DigitalModel;
import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

@EntityView(DigitalModel.class)
@CreatableEntityView
@UpdatableEntityView
public interface DigitalModelUpdateView {

    @IdMapping
    Long getId();

    Long getDigitalModelId();

    void setDigitalModelId(Long digitalModelId);

    String getTag();

    void setTag(String tag);

}
