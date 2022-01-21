package cloud.officinaedisanto.heritage.objectspacemanagementservice.model.views.bo;

import cloud.officinaedisanto.heritage.objectspacemanagementservice.model.LocalizableEntity;
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
        @JsonSubTypes.Type(value = LocalizableEntityView.class, name = "entity"),
        @JsonSubTypes.Type(value = SpaceView.class, name = "space"),
})
@JsonTypeName("entity")
public interface LocalizableEntityView {

    @IdMapping
    Long getId();

    String getEntityId();

}
