package cloud.officinaedisanto.heritage.objectspacemanagementservice.api.backoffice;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.LocationReferenceView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.updatable.LocationReferenceUpdateView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.services.LocationReferencesService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Tag(name = "locations-backoffice")
@Path("/api/v1/bo/entity")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class LocationReferencesBOResource {

    private final LocationReferencesService locationReferencesService;

    public LocationReferencesBOResource(LocationReferencesService locationReferencesService) {
        this.locationReferencesService = locationReferencesService;
    }

    @GET
    @Path("/location/{locationId}")
    public LocationReferenceView get(@PathParam("locationId") long locationId) {
        return locationReferencesService.get(locationId);
    }

    @GET
    @Path("/{id}/locations")
    public List<LocationReferenceView> getAll(@PathParam("id") long id) {
        return locationReferencesService.getAll(id);
    }

    @POST
    @Path("/{id}/locations")
    public LocationReferenceView save(@PathParam("id") long id, LocationReferenceUpdateView locationReference) {
        return locationReferencesService.save(id, locationReference);
    }

    @PUT
    @Path("/{id}/locations/{locationId}")
    public LocationReferenceView update(@PathParam("id") long id, @PathParam("locationId") long locationId,
                                        LocationReferenceUpdateView locationReference) {
        return locationReferencesService.update(id, locationId, locationReference);
    }

    @DELETE
    @Path("/{id}/locations/{locationId}")
    public void delete(@PathParam("id") long id, @PathParam("locationId") long locationId) {
        locationReferencesService.delete(id, locationId);
    }

}
