package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.GeospatialReference;
import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;

@EntityView(GeospatialReference.class)
@CreatableEntityView
@UpdatableEntityView
@JsonTypeName("geo")
public interface GeospatialReferenceUpdateView extends LocationReferenceUpdateView {

    Point<G2D> getCenterPoint();

    void setCenterPoint(Point<G2D> centerPoint);

    Polygon<G2D> getPolygon();

    void setPolygon(Polygon<G2D> polygon);

}
