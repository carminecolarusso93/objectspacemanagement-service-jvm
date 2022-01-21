package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.QRCodeReference;
import com.blazebit.persistence.view.EntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;

@EntityView(QRCodeReference.class)
@JsonTypeName("qrcode")
public interface QRCodeReferenceView extends LocationReferenceView {

    String getQrCode();

}
