package cloud.officinaedisanto.heritage.objectspacemanagementservice.model;

import javax.persistence.Entity;

@Entity
public class DigitalModel extends BaseEntity {

    private long digitalModelId;

    private String tag;

    public long getDigitalModelId() {
        return digitalModelId;
    }

    public void setDigitalModelId(long digitalModelId) {
        this.digitalModelId = digitalModelId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
