package be.artisjaap.common.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NotEqualsTo {
    String field();

	String errorKey() default "";
}
