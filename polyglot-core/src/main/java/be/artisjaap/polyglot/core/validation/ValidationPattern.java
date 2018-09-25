package be.artisjaap.polyglot.core.validation;

public interface ValidationPattern {
    public final static String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
	public final static String GMAIL = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
}
