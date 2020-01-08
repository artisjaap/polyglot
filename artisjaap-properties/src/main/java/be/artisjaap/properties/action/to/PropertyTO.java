package be.artisjaap.properties.action.to;

import be.artisjaap.properties.model.PropertyType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PropertyTO {
    private String key;
    private String value;
    private PropertyType actualType;


}
