package be.artisjaap.properties.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProperty {
    @Autowired
    private PropertyCache propertyCache;

    public <T> T withKey(String key){
        return propertyCache.propertyForKey(key);

    }
}
