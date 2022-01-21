package cloud.officinaedisanto.heritage.objectspacemanagementservice.services;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.LocalizableEntity;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.LocationReference;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.LocationReferenceView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable.LocationReferenceUpdateView;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
@Transactional
public class LocationReferencesService {

    private final EntityManager em;
    private final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;

    public LocationReferencesService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    //Support methods

    public LocationReference getLocationReference(long locationId) {
        var locationReference = em.find(LocationReference.class, locationId);
        if (locationReference == null) {
            throw new NotFoundException();
        }
        return locationReference;
    }

    //Backoffice methods

    public LocationReferenceView get(long id) {
        var locationReferenceView = evm.find(em, LocationReferenceView.class, id);
        if (locationReferenceView == null) {
            throw new NotFoundException();
        }
        return locationReferenceView;
    }

    public List<LocationReferenceView> getAll(long id) {
        var criteriaBuilder = cbf.create(em, Long.class)
                .from(LocalizableEntity.class, "entity")
                .where("entity.id").eq(id)
                .select("entity.locationReferences.id");
        var returnCriteriaBuilder = cbf.create(em, LocationReference.class)
                .where("id").in(criteriaBuilder.getResultList());
        return evm.applySetting(EntityViewSetting.create(LocationReferenceView.class), returnCriteriaBuilder)
                .getResultList();
    }

    public LocationReferenceView save(long id, LocationReferenceUpdateView locationReference) {
        var criteriaBuilder = cbf.create(em, LocalizableEntity.class)
                .where("id").eq(id);
        if (criteriaBuilder.getResultList() == null || criteriaBuilder.getResultList().isEmpty()) {
            throw new NotFoundException();
        }
        var entity = criteriaBuilder.getSingleResult();
        evm.save(em, locationReference);
        entity.getLocationReferences().add(getLocationReference(locationReference.getId()));
        em.persist(entity);
        return evm.find(em, LocationReferenceView.class, locationReference.getId());
    }

    public LocationReferenceView update(long id, long locationId, LocationReferenceUpdateView locationReference) {
        evm.save(em, locationReference);
        return evm.find(em, LocationReferenceView.class, locationId);
    }

    public void delete(long id, long locationId) {
        var location = getLocationReference(locationId);
        var criteriaBuilder = cbf.create(em, LocalizableEntity.class)
                .where("id").eq(id);
        if (criteriaBuilder.getResultList() == null || criteriaBuilder.getResultList().isEmpty()) {
            throw new NotFoundException();
        }
        var entity = criteriaBuilder.getSingleResult();
        entity.getLocationReferences().removeIf(lr -> lr.getId() == locationId);
        em.persist(entity);
        em.remove(location);
    }

}
