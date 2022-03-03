package cloud.officinaedisanto.heritage.objectspacemanagementservice.services;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.DigitalModel;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.Space;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.DigitalModelView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.LocalizableEntityView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.SpaceView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable.DigitalModelUpdateView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable.SpaceUpdateView;
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
public class SpacesService {

    private final EntityManager em;
    private final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;

    private final LocalizableEntitiesService localizableEntitiesService;

    public SpacesService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm,
                         LocalizableEntitiesService localizableEntitiesService) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
        this.localizableEntitiesService = localizableEntitiesService;
    }

    //Support methods

    public Space getSpace(long id) {
        var space = em.find(Space.class, id);
        if (space == null) {
            throw new NotFoundException();
        }
        return space;
    }

    //Backoffice methods

    public SpaceView get(long id) {
        var spaceView = evm.find(em, SpaceView.class, id);
        if (spaceView == null) {
            throw new NotFoundException();
        }
        return spaceView;
    }

    public List<SpaceView> getAll(String uuid, Integer major, Integer minor, Double x, Double y, Double lat, Double lng,
                                  Integer distance, String qrCode, String entityId) {
        var criteriaBuilder = cbf.create(em, Space.class);
        if (uuid != null && major != null && minor != null) {
            criteriaBuilder
                    .where("TREAT(locationReferences as BeaconReference).uuid").eq(uuid)
                    .where("TREAT(locationReferences as BeaconReference).major").eq(major)
                    .where("TREAT(locationReferences as BeaconReference).minor").eq(minor);
        }
        if (x != null && y != null && distance != null) {
            criteriaBuilder
                    .setWhereExpression("distance(TREAT(locationReferences as CartesianReference).centerPoint, :point) " + "<= :distance")
                    .setParameter("point", point(PROJECTED_2D_METER, c(x, y))).setParameter("distance", distance);
        }
        if (lat != null && lng != null && distance != null) {
            criteriaBuilder
                    .setWhereExpression("distance(TREAT(locationReferences as GeospatialReference).centerPoint, :point) " + "<= :distance")
                    .setParameter("point", point(WGS84, g(lng, lat))).setParameter("distance", (double) distance);
        }
        if (qrCode != null && !qrCode.isBlank()) {
            criteriaBuilder.where("TREAT(locationReferences as QRCodeReference).qrCode").eq(qrCode);
        }
        if (entityId != null && !entityId.isBlank()) {
            criteriaBuilder.where("entityId").eq(entityId);
        }
        return evm.applySetting(EntityViewSetting.create(SpaceView.class), criteriaBuilder).getResultList();
    }

    public SpaceView save(SpaceUpdateView space) {
        evm.save(em, space);
        return evm.find(em, SpaceView.class, space.getId());
    }

    public SpaceView update(SpaceUpdateView space) {
        return save(space);
    }

    public void delete(long id) {
        var space = getSpace(id);
        em.remove(space);
    }

    //Digital models

    public List<DigitalModelView> getDigitalModels(long id, String tag) {
        var criteriaBuilder = cbf.create(em, Long.class)
                .from(Space.class, "space")
                .where("space.id").eq(id)
                .select("space.digitalModels.id");
        var returnCriteriaBuilder = cbf.create(em, DigitalModel.class)
                .where("id").in(criteriaBuilder.getResultList());
        if (tag != null) {
            returnCriteriaBuilder.where("tag").eq(tag);
        }
        return evm.applySetting(EntityViewSetting.create(DigitalModelView.class), returnCriteriaBuilder)
                .getResultList();
    }

    public DigitalModelView saveDigitalModel(long id, DigitalModelUpdateView digitalModel) {
        var criteriaBuilder = cbf.create(em, Space.class).where("id").eq(id);
        if (criteriaBuilder.getResultList() == null || criteriaBuilder.getResultList().isEmpty()) {
            throw new NotFoundException();
        }
        var space = criteriaBuilder.getSingleResult();
        evm.save(em, digitalModel);
        space.getDigitalModels().add(getDigitalModel(digitalModel.getId()));
        em.persist(space);
        return evm.find(em, DigitalModelView.class, digitalModel.getId());
    }

    public DigitalModelView updateDigitalModel(long id, long digitalModelId, DigitalModelUpdateView digitalModel) {
        evm.save(em, digitalModel);
        return evm.find(em, DigitalModelView.class, digitalModelId);
    }

    public void deleteDigitalModel(long id, long digitalModelId) {
        var digitalModel = getDigitalModel(digitalModelId);
        var criteriaBuilder = cbf.create(em, Space.class).where("id").eq(id);
        if (criteriaBuilder.getResultList() == null || criteriaBuilder.getResultList().isEmpty()) {
            throw new NotFoundException();
        }
        var space = criteriaBuilder.getSingleResult();
        space.getDigitalModels().removeIf(lr -> lr.getId() == digitalModelId);
        em.persist(space);
        em.remove(digitalModel);
    }

    private DigitalModel getDigitalModel(long digitalModelId) {
        var digitalModel = em.find(DigitalModel.class, digitalModelId);
        if (digitalModel == null) {
            throw new NotFoundException();
        }
        return digitalModel;
    }

    //Contained spaces

    public List<SpaceView> getContainedSpaces(long id) {
        var criteriaBuilder = cbf.create(em, Long.class).from(Space.class, "space")
                .where("space.id").eq(id).select("space.containedSpaces.id");
        var csCriteriaBuilder = cbf.create(em, Space.class).where("id")
                .in(criteriaBuilder.getResultList());
        return evm.applySetting(EntityViewSetting.create(SpaceView.class), csCriteriaBuilder).getResultList();
    }

    public void addContainedSpace(long id, long containedSpaceId) {
        var space = getSpace(id);
        var containedSpace = getSpace(containedSpaceId);
        space.getContainedSpaces().add(containedSpace);
        em.persist(space);
    }

    public void removeContainedSpace(long id, long containedSpaceId) {
        var space = getSpace(id);
        space.getContainedSpaces().removeIf(s -> s.getId() == containedSpaceId);
        em.persist(space);
    }

    //Hosted entities

    public List<LocalizableEntityView> getHostedEntities(long id) {
        var criteriaBuilder = cbf.create(em, Long.class).from(Space.class, "space")
                .where("space.id").eq(id).select("space.hostedEntities.id");
        return localizableEntitiesService.getByIds(criteriaBuilder.getResultList());
    }

    public void addHostedEntity(long id, long hostedEntityId) {
        var space = getSpace(id);
        var hostedEntity = localizableEntitiesService.getEntity(hostedEntityId);
        space.getHostedEntities().add(hostedEntity);
        em.persist(space);
    }

    public void removeHostedEntity(long id, long hostedEntityId) {
        var space = getSpace(id);
        space.getHostedEntities().removeIf(e -> e.getId() == hostedEntityId);
        em.persist(space);
    }

}
