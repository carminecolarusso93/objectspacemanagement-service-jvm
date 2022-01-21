package cloud.officinaedisanto.heritage.objectspacemanagementservice.api.backoffice;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.LocalizableEntityView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable.LocalizableEntityUpdateView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.services.LocalizableEntitiesService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Tag(name = "entities-backoffice")
@Path("/api/v1/bo/entities")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class LocalizableEntitiesBOResource {

    @Inject
    LocalizableEntitiesService localizableEntitiesService;

    @GET
    @Path("/{id}")
    public LocalizableEntityView get(@PathParam("id") long id) {
        return localizableEntitiesService.get(id);
    }

    @GET
    public List<LocalizableEntityView> getAll(@QueryParam("uuid") String uuid,
                                              @QueryParam("major") Integer major,
                                              @QueryParam("minor") Integer minor,
                                              @QueryParam("x") Double x,
                                              @QueryParam("y") Double y,
                                              @QueryParam("lat") Double lat,
                                              @QueryParam("lng") Double lng,
                                              @QueryParam("distance") Integer distance,
                                              @QueryParam("qrCode") String qrCode,
                                              @QueryParam("entityId") String entityId) {
        return localizableEntitiesService.getAll(uuid, major, minor, x, y, lat, lng, distance, qrCode, entityId);
    }

    @POST
    public LocalizableEntityView save(LocalizableEntityUpdateView entity) {
        return localizableEntitiesService.save(entity);
    }

    @PUT
    @Path("/{id}")
    public LocalizableEntityView update(@PathParam("id") long id, LocalizableEntityUpdateView entity) {
        return localizableEntitiesService.update(entity);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        localizableEntitiesService.delete(id);
    }

}
