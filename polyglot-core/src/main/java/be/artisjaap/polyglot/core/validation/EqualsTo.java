package be.artisjaap.polyglot.core.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EqualsTo {
    String field();

	String errorKey() default "";
}
