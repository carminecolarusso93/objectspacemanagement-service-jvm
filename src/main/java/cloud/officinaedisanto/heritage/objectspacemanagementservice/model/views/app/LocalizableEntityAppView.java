package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.app;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.LocalizableEntity;
import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo.SpaceView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;
import com.blazebit.persistence.view.IdMapping;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@EntityView(LocalizableEntity.class)
@EntityViewInheritance
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LocalizableEntityAppView.class, name = "entity"),
        @JsonSubTypes.Type(value = SpaceAppView.class, name = "space"),
})
@JsonTypeName("entity")
public interface LocalizableEntityAppView {

    @IdMapping
    Long getId();

    String getEntityId();

}
