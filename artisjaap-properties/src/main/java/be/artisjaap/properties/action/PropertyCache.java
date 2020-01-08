package be.artisjaap.properties.action;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.properties.action.assembler.PropertyAssembler;
import be.artisjaap.properties.action.to.PropertyTO;
import be.artisjaap.properties.model.Property;
import be.artisjaap.properties.model.PropertyType;
import be.artisjaap.properties.model.mongo.PropertyRepository;
import org.apache.el.parser.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class PropertyCache {

    private Map<String, PropertyTO> properties = new HashMap<>();

    private final PropertyRepository propertyRepository;

    private final PropertyAssembler propertyAssembler;

    public PropertyCache(PropertyRepository propertyRepository, PropertyAssembler propertyAssembler) {
        this.propertyRepository = propertyRepository;
        this.propertyAssembler = propertyAssembler;
    }

    @PostConstruct
    private void initProperties() {
        propertyRepository.findAll().stream().map(propertyAssembler::assembleTO).forEach(p -> properties.put(p.getKey(), p));
    }

    public String propertyForKey(String key) {
        PropertyTO value = properties.get(key);
        if (value == null) {
            throw new UnsupportedOperationException("property not found: " + key);
        }
        return value.getValue();
    }


    public Boolean propertyForKeyAsBoolean(String key) {
        PropertyTO value = properties.get(key);
        if (value == null) {
            throw new UnsupportedOperationException("property not found: " + key);
        }
        if(value.getActualType() != PropertyType.BOOLEAN){
            throw new IllegalStateException(String.format("Property with value %s cannot be converted to boolean", value.getValue()));
        }
        return Boolean.parseBoolean(value.getValue());
    }


    public void updateOrCreate(String key, Object value) {
        PropertyType type = PropertyType.forClass(value.getClass());
        Property property = Property.newBuilder().withKey(key)
                .withType(type)
                .withValue(toStringValue(value, type))
                .build();
        properties.put(key, propertyAssembler.assembleTO(property));
        propertyRepository.createOrUpdate(property);
    }

    public Set<PropertyTO> allProperties(){
        return properties.values().stream().collect(Collectors.toSet());
    }

    private String toStringValue(Object value, PropertyType type) {
        switch (type) {
            case DATE:
                return LocalDateUtils.formatIsoDate((LocalDate) value);
            case DATE_TIME:
                return LocalDateUtils.formatIsoDate((LocalDateTime) value);
            default:
                return value.toString();

        }
    }


}
