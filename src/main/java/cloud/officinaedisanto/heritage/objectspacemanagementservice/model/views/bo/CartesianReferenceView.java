package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.CartesianReference;
import com.blazebit.persistence.view.EntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;

@EntityView(CartesianReference.class)
@JsonTypeName("cartesian")
public interface CartesianReferenceView extends LocationReferenceView {

    Point<C2D> getCenterPoint();

    Polygon<C2D> getPolygon();

}
