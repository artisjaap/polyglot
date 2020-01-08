package be.artisjaap.properties.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetProperty {
    private final PropertyCache propertyCache;

    public SetProperty(PropertyCache propertyCache) {
        this.propertyCache = propertyCache;
    }


    public void forKeyWithValue(String key, Object value){
        propertyCache.updateOrCreate(key, value);
    }
}
