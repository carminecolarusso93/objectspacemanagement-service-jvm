package cloud.officinaedisanto.heritage.objectspacemanagementservice.api.backoffice;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.DigitalModelView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.LocalizableEntityView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.SpaceView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable.DigitalModelUpdateView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable.SpaceUpdateView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.services.SpacesService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Tag(name = "spaces-backoffice")
@Path("/api/v1/bo/spaces")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class SpacesBOResource {

    @Inject
    SpacesService spacesService;

    @GET
    @Path("/{id}")
    public SpaceView get(@PathParam("id") long id) {
        return spacesService.get(id);
    }

    @GET
    public List<SpaceView> getAll(@QueryParam("uuid") String uuid,
                                  @QueryParam("major") Integer major,
                                  @QueryParam("minor") Integer minor,
                                  @QueryParam("x") Double x,
                                  @QueryParam("y") Double y,
                                  @QueryParam("lat") Double lat,
                                  @QueryParam("lng") Double lng,
                                  @QueryParam("distance") Integer distance,
                                  @QueryParam("qrCode") String qrCode,
                                  @QueryParam("entityId") String entityId) {
        return spacesService.getAll(uuid, major, minor, x, y, lat, lng, distance, qrCode, entityId);
    }

    @POST
    public SpaceView save(SpaceUpdateView space) {
        return spacesService.save(space);
    }

    @PUT
    @Path("/{id}")
    public SpaceView update(@PathParam("id") long id, SpaceUpdateView space) {
        return spacesService.update(space);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        spacesService.delete(id);
    }

    //Digital models

    @GET
    @Path("/{id}/digitalmodels")
    public List<DigitalModelView> getDigitalModels(@PathParam("id") long id) {
        return spacesService.getDigitalModels(id);
    }

    @POST
    @Path("/{id}/digitalmodels")
    public DigitalModelView saveDigitalModel(@PathParam("id") long id, DigitalModelUpdateView digitalModel) {
        return spacesService.saveDigitalModel(id, digitalModel);
    }

    @PUT
    @Path("/{id}/digitalmodels/{digitalModelId}")
    public DigitalModelView updateDigitalModel(@PathParam("id") long id, @PathParam("digitalModelId") long digitalModelId,
                                   DigitalModelUpdateView digitalModel) {
        return spacesService.updateDigitalModel(id, digitalModelId, digitalModel);
    }

    @DELETE
    @Path("/{id}/digitalmodels/{digitalModelId}")
    public void deleteDigitalModel(@PathParam("id") long id, @PathParam("digitalModelId") long digitalModelId) {
        spacesService.deleteDigitalModel(id, digitalModelId);
    }

    //Contained spaces

    @GET
    @Path("/{id}/containedspaces")
    public List<SpaceView> getContainedSpaces(@PathParam("id") long id) {
        return spacesService.getContainedSpaces(id);
    }

    @PUT
    @Path("/{id}/containedspaces/{containedSpaceId}")
    public void addContainedSpace(@PathParam("id") long id, @PathParam("containedSpaceId") long containedSpaceId) {
        spacesService.addContainedSpace(id, containedSpaceId);
    }

    @DELETE
    @Path("/{id}/containedspaces/{containedSpaceId}")
    public void removeContainedSpace(@PathParam("id") long id, @PathParam("containedSpaceId") long containedSpaceId) {
        spacesService.removeContainedSpace(id, containedSpaceId);
    }

    //Hosted entities

    @GET
    @Path("/{id}/hostedentities")
    public List<LocalizableEntityView> getHostedEntities(@PathParam("id") long id) {
        return spacesService.getHostedEntities(id);
    }

    @PUT
    @Path("/{id}/hostedentities/{hostedEntityId}")
    public void addHostedEntity(@PathParam("id") long id, @PathParam("hostedEntityId") long hostedEntityId) {
        spacesService.addHostedEntity(id, hostedEntityId);
    }

    @DELETE
    @Path("/{id}/hostedentities/{hostedEntityId}")
    public void removeHostedEntity(@PathParam("id") long id, @PathParam("hostedEntityId") long hostedEntityId) {
        spacesService.removeHostedEntity(id, hostedEntityId);
    }

}
