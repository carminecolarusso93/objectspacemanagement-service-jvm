package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;

import javax.persistence.Entity;

@Entity
@JsonTypeName("geo")
public class GeospatialReference extends LocationReference {

    private Point<G2D> centerPoint;

    private Polygon<G2D> polygon;

    public Point<G2D> getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Point<G2D> centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Polygon<G2D> getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon<G2D> polygon) {
        this.polygon = polygon;
    }

}
