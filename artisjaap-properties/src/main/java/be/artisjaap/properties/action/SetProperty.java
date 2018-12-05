package be.artisjaap.properties.action;

import be.artisjaap.properties.model.Property;
import be.artisjaap.properties.model.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetProperty {
    @Autowired
    private PropertyCache propertyCache;


    public void forKeyWithValue(String key, Object value){
        propertyCache.updateOrCreate(key, value);
    }
}
