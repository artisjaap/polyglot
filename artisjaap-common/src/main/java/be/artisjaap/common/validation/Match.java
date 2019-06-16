package be.artisjaap.common.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Match {
    String pattern();

	String errorKey();
}
