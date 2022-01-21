package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("qrcode")
public class QRCodeReference extends LocationReference {

    private String qrCode;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

}
