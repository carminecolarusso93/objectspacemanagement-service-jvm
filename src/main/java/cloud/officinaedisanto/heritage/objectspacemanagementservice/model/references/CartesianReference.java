package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.DigitalModel;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@JsonTypeName("cartesian")
public class CartesianReference extends LocationReference {

    private Point<C2D> centerPoint;

    private Polygon<C2D> polygon;

    @ManyToOne
    private DigitalModel referenceModel;

    public Point<C2D> getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Point<C2D> centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Polygon<C2D> getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon<C2D> polygon) {
        this.polygon = polygon;
    }

    public DigitalModel getReferenceModel() {
        return referenceModel;
    }

    public void setReferenceModel(DigitalModel referenceModel) {
        this.referenceModel = referenceModel;
    }

}
