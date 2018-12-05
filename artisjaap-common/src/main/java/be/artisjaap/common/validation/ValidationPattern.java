package be.artisjaap.common.validation;

public interface ValidationPattern {
    String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    String GMAIL = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
}
