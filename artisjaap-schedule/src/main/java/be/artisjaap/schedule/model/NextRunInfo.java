package be.artisjaap.schedule.model;

import java.util.HashMap;
import java.util.Map;

public class NextRunInfo {
    private Boolean parameterized = false;
	private Map<String, Object> properties = new HashMap<>();
	public Boolean getParameterized() {
		return parameterized;
	}
	public void setParameterized(Boolean parameterized) {
		this.parameterized = parameterized;
	}
	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	
}
