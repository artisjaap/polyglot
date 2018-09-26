package be.artisjaap.core.validation;

import java.util.HashMap;
import java.util.Map;

public class ValidationParameterBuilder {
    private Map<String, String> params = new HashMap<>();

	Map<String, String> getParams() {
		return params;
	}

	private ValidationParameterBuilder() {

	}

	public static ValidationParameterBuilder create() {
		return new ValidationParameterBuilder();
	}

	public ValidationParameterBuilder voegToe(String key, String value) {
		params.put(key, value);
		return this;
	}

}
