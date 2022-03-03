package cloud.officinaedisanto.heritage.objectspacemanagementservice.services;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.LocalizableEntity;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.LocationReference;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.app.LocalizableEntityAppView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.LocalizableEntityView;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@ApplicationScoped
@Transactional
public class LocationsService {

    private final EntityManager em;
    private final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;

    public LocationsService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    public List<LocalizableEntityAppView> localize(String uuid, Integer major, Integer minor, Double lat, Double lng,
                                                   Integer distance, String qrCode) {
        var criteriaBuilder = cbf.create(em, LocalizableEntity.class);
        if (uuid != null && major != null && minor != null) {
            criteriaBuilder
                    .where("TREAT(locationReferences as BeaconReference).uuid").eq(uuid)
                    .where("TREAT(locationReferences as BeaconReference).major").eq(major)
                    .where("TREAT(locationReferences as BeaconReference).minor").eq(minor);
        }
        if (lat != null && lng != null && distance != null) {
            criteriaBuilder
                    .setWhereExpression("distance(TREAT(locationReferences as GeospatialReference).centerPoint, :point) " +
                            "<= :distance")
                    .setParameter("point", point(WGS84, g(lng, lat)))
                    .setParameter("distance", (double) distance);
        }
        if (qrCode != null && !qrCode.isBlank()) {
            criteriaBuilder
                    .where("TREAT(locationReferences as QRCodeReference).qrCode").eq(qrCode);
        }
        return evm.applySetting(EntityViewSetting.create(LocalizableEntityAppView.class), criteriaBuilder)
                .getResultList();
    }

    public void updateUserLocation(String userId, LocationReference location) {
        var criteriaBuilder = cbf.create(em, LocalizableEntity.class)
                .where("entityId").eq(userId);
        LocalizableEntity userLocation;
        if (!criteriaBuilder.getResultList().isEmpty()) {
            userLocation = criteriaBuilder.getSingleResult();
        } else {
            userLocation = new LocalizableEntity();
            userLocation.setEntityId(userId);
        }
        userLocation.getLocationReferences().removeIf(lr -> lr.getClass().equals(location.getClass()));
        userLocation.getLocationReferences().add(location);
        em.persist(userLocation);
    }

}
