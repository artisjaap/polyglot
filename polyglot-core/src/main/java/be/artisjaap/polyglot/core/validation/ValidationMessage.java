package be.artisjaap.polyglot.core.validation;

import java.util.HashMap;
import java.util.Map;

public class ValidationMessage {
    public final static String ALGEMEEN = "algemeen";

	private String message;
	private String key;
	private Map<String, String> params = new HashMap<>();

	public ValidationMessage() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public ValidationMessage(String key, String message) {
		super();
		this.key = key;
		this.message = message;
	}

	public ValidationMessage(String key, String message, ValidationParameterBuilder params) {
		super();
		this.key = key;
		this.message = message;
		this.params = params.getParams();
	}

}