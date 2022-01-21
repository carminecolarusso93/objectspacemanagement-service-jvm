package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.CartesianReference;
import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;

@EntityView(CartesianReference.class)
@CreatableEntityView
@UpdatableEntityView
@JsonTypeName("cartesian")
public interface CartesianReferenceUpdateView extends LocationReferenceUpdateView {

    Point<C2D> getCenterPoint();

    void setCenterPoint(Point<C2D> centerPoint);

    Polygon<C2D> getPolygon();

    void setPolygon(Polygon<C2D> polygon);

}
