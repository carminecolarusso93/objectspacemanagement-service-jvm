package cloud.officinaedisanto.heritage.objectspacemanagementservice.api.app;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.references.LocationReference;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.LocalizableEntityView;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.services.LocationsService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Tag(name = "locations-app")
@Path("/api/v1/app/locations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationsAppResource {

    private final LocationsService locationsService;

    public LocationsAppResource(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GET
    public List<LocalizableEntityView> localize(@QueryParam("major") Integer major,
                                                @QueryParam("minor") Integer minor,
                                                @QueryParam("lat") Double lat,
                                                @QueryParam("lng") Double lng,
                                                @QueryParam("distance") Integer distance,
                                                @QueryParam("qrCode") String qrCode) {
        return locationsService.localize(major, minor, lat, lng, distance, qrCode);
    }

    @PUT
    @Path("/users/{userId}")
    public Response updateUserLocation(@PathParam("userId") String userId, LocationReference location) {
        locationsService.updateUserLocation(userId, location);
        return Response.ok().build();
    }

}
