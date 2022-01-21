package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.GeospatialReference;
import com.blazebit.persistence.view.EntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;

@EntityView(GeospatialReference.class)
@JsonTypeName("geo")
public interface GeospatialReferenceView extends LocationReferenceView {

    Point<G2D> getCenterPoint();

    Polygon<G2D> getPolygon();

}
