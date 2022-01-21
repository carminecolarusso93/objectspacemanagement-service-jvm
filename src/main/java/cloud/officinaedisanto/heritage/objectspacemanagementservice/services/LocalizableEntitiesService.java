package cloud.officinaedisanto.heritage.objectspacemanagementservice.services;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.LocalizableEntity;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.LocalizableEntityView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable.LocalizableEntityUpdateView;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.PROJECTED_2D_METER;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@ApplicationScoped
@Transactional
public class LocalizableEntitiesService {

    private final EntityManager em;
    private final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;

    public LocalizableEntitiesService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    //Support methods

    public LocalizableEntity getEntity(long id) {
        var entity = em.find(LocalizableEntity.class, id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;
    }

    public List<LocalizableEntityView> getByIds(List<Long> ids) {
        var criteriaBuilder = cbf.create(em, LocalizableEntity.class)
                .where("id").in(ids);
        return evm.applySetting(EntityViewSetting.create(LocalizableEntityView.class), criteriaBuilder)
                .getResultList();
    }

    //Backoffice methods

    public LocalizableEntityView get(long id) {
        var entityView = evm.find(em, LocalizableEntityView.class, id);
        if (entityView == null) {
            throw new NotFoundException();
        }
        return entityView;
    }

    public List<LocalizableEntityView> getAll(String uuid, Integer major, Integer minor, Double x, Double y, Double lat,
                                              Double lng, Integer distance, String qrCode, String entityId) {
        var criteriaBuilder = cbf.create(em, LocalizableEntity.class)
                .where("TYPE(localizableEntity)").eq(LocalizableEntity.class);
        if (uuid != null && major != null && minor != null) {
            criteriaBuilder
                    .where("TREAT(locationReferences as BeaconLocationReference).uuid").eq(uuid)
                    .where("TREAT(locationReferences as BeaconLocationReference).major").eq(major)
                    .where("TREAT(locationReferences as BeaconLocationReference).minor").eq(minor);
        }
        if (x != null && y != null && distance != null) {
            criteriaBuilder
                    .setWhereExpression("distance(TREAT(locationReferences as CartesianLocationReference).centerPoint, :point) " +
                            "<= :distance")
                    .setParameter("point", point(PROJECTED_2D_METER, c(x, y)))
                    .setParameter("distance", distance);
        }
        if (lat != null && lng != null && distance != null) {
            criteriaBuilder
                    .setWhereExpression("distance(TREAT(locationReferences as GeoLocationReference).centerPoint, :point) " +
                            "<= :distance")
                    .setParameter("point", point(WGS84, g(lng, lat)))
                    .setParameter("distance", distance);
        }
        if (qrCode != null && !qrCode.isBlank()) {
            criteriaBuilder
                    .where("TREAT(locationReferences as QRCodeLocationReference).qrCode").eq(qrCode);
        }
        if (entityId != null && !entityId.isBlank()) {
            criteriaBuilder
                    .where("entityId").eq(entityId);
        }
        return evm.applySetting(EntityViewSetting.create(LocalizableEntityView.class), criteriaBuilder)
                .getResultList();
    }

    public LocalizableEntityView save(LocalizableEntityUpdateView entity) {
        evm.save(em, entity);
        return evm.find(em, LocalizableEntityView.class, entity.getId());
    }

    public LocalizableEntityView update(LocalizableEntityUpdateView entity) {
        return save(entity);
    }

    public void delete(long id) {
        var entity = getEntity(id);
        em.remove(entity);
    }

}
