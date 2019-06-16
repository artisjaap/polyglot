package be.artisjaap.properties.model.mongo;

import be.artisjaap.properties.model.Property;

public interface PropertyRepositoryCustom {
    void createOrUpdate(Property property);
}
