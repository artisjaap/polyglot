package be.artisjaap.polyglot.core.validators;

import be.artisjaap.core.validation.ValidatorRecord;

public enum DefaultValidations implements ValidatorRecord {


    USERNAME_ALREADY_EXISTS("Username already exists");

    private final String value;

    DefaultValidations(String value) {
        this.value = value;
    }

    @Override
    public String key() {
        return name();
    }

    @Override
    public String value() {
        return value;
    }
}
