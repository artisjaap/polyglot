package be.artisjaap.schedule.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaakConfig {

    private Map<String, Object> properties = new HashMap<>();
	
	public <T> T property(String key){
		return (T) properties.get(key);
	}
	
	public Map<String, Object> getProperties() {
		return properties;
	}
	
	public List<TaakParameterEntry> alleProperties() {
		return properties.entrySet().stream().map(e -> TaakParameterEntry.from(e.getKey(), e.getValue())).collect(Collectors.toList());

	}
	
	public static TaakConfig forParameters(Map<String, Object> params){
		TaakConfig config = new TaakConfig();
		config.properties = params;
		return config;
	}
}
