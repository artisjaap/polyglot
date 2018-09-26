package be.artisjaap.core.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Required {

	String errorKey() default "";
}