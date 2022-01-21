package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.QRCodeReference;
import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;

@EntityView(QRCodeReference.class)
@CreatableEntityView
@UpdatableEntityView
@JsonTypeName("qrcode")
public interface QRCodeReferenceUpdateView extends LocationReferenceUpdateView {

    String getQrCode();

    void setQrCode(String qrCode);

}
