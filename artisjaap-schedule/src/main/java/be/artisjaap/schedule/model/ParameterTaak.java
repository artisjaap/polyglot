package be.artisjaap.schedule.model;

import java.util.Map;
import java.util.Optional;

public interface ParameterTaak {
    Map<String, Class> supportedProperties();
	
	Map<String, Object> definedProperties();
	
	default <T> Optional<T> property(String key){
		Class<T> clazz = supportedProperties().get(key);
		if(clazz == null){
			throw new UnsupportedOperationException("parameter key is ongeldig voor deze taak");
		}
		
		Object value = definedProperties().get(key);
		if(value == null){
			return Optional.empty();
		}
		
		if(clazz.isAssignableFrom(value.getClass())){
			
			return Optional.of(clazz.cast(value));
		}
		throw new UnsupportedOperationException("parameter is niet van het juiste type");
	}
}
