package be.artisjaap.polyglot.core.validation;

import java.util.HashSet;
import java.util.Set;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private final Set<ValidationMessage> messages;

	public ValidationException(Set<ValidationMessage> messages) {
		super();
		this.messages = messages;
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
		this.messages = new HashSet<>();
		this.messages.add(new ValidationMessage(ValidationMessage.ALGEMEEN, message));
	}

	public ValidationException(String message) {
		super(message);
		this.messages = new HashSet<>();
		this.messages.add(new ValidationMessage(ValidationMessage.ALGEMEEN, message));
	}

	public Set<ValidationMessage> getMessages() {
		return messages;
	}

}
