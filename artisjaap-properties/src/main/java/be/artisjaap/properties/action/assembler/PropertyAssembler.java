package be.artisjaap.properties.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.properties.action.to.PropertyTO;
import be.artisjaap.properties.model.Property;
import org.springframework.stereotype.Component;

@Component
public class PropertyAssembler implements Assembler<PropertyTO, Property> {
    @Override
    public PropertyTO assembleTO(Property doc) {
        return PropertyTO.builder()
                .key(doc.getKey())
                .value(doc.getValue())
                .actualType(doc.getType())
                .build();
    }

    @Override
    public Property assembleEntity(PropertyTO propertyTO) {
        return null;
    }
}
