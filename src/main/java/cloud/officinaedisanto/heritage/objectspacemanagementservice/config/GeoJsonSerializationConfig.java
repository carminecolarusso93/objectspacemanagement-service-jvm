package cloud.officinaedisanto.heritage.objectspacemanagementservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;
import org.geolatte.geom.json.GeolatteGeomModule;
import org.geolatte.geom.json.Setting;

import javax.inject.Singleton;

@Singleton
public class GeoJsonSerializationConfig implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        GeolatteGeomModule module = new GeolatteGeomModule();
        module.set(Setting.SUPPRESS_CRS_SERIALIZATION, true);
        objectMapper.registerModule(module);
    }

}
