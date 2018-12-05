package be.artisjaap.properties.action;

import be.artisjaap.properties.model.Property;
import be.artisjaap.properties.model.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
class PropertyCache {

    private Map<String, Object> properties = new HashMap<>();

    @Autowired
    private PropertyRepository propertyRepository;


    @PostConstruct
    private void initProperties() {
        propertyRepository.findAll().forEach(p -> properties.put(p.getKey(), p.getValue()));
    }

    public <T> T propertyForKey(String key){
        Object value = properties.get(key);
        if(value == null){
            throw new UnsupportedOperationException("property not found: " + key);
        }
        return (T) value;
    }

    public void updateOrCreate(String key, Object value){
        properties.put(key, value);
        propertyRepository.createOrUpdate(Property.newBuilder().withKey(key).withValue(value).build());
    }

}