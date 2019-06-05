package be.artisjaap.properties.model.mongo;

import be.artisjaap.properties.model.Property;
import org.springframework.beans.factory.annotation.Autowired;

public class PropertyRepositoryImpl implements PropertyRepositoryCustom {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public void createOrUpdate(Property property) {
        Property newProperty = propertyRepository.findByKey(property.getKey()).map(p -> {
            p.setValue(property.getValue());
            return p;
        }).orElse(property);

        propertyRepository.save(newProperty);
    }
}
