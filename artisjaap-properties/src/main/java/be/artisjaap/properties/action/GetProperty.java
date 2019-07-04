package be.artisjaap.properties.action;

import be.artisjaap.properties.action.to.PropertyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GetProperty {
    @Autowired
    private PropertyCache propertyCache;

    public String valueForKey(String key){
        return propertyCache.propertyForKey(key);
    }

    public Boolean valueForKeyAsBoolean(String key){
        return propertyCache.propertyForKeyAsBoolean(key);
    }

    public Set<PropertyTO> allProperties(){
        return propertyCache.allProperties();
    }
}
