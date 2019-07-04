package be.artisjaap.properties.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.properties.action.to.PropertyTO;
import be.artisjaap.properties.model.Property;
import org.springframework.stereotype.Component;

@Component
public class PropertyAssembler implements Assembler<PropertyTO, Property> {
    @Override
    public PropertyTO assembleTO(Property doc) {
        return PropertyTO.newBuilder()
                .withKey(doc.getKey())
                .withValue(doc.getValue())
                .withActualType(doc.getType())
                .build();
    }

    @Override
    public Property assembleEntity(PropertyTO propertyTO) {
        return null;
    }
}
