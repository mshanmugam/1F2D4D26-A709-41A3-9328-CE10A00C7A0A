package com.sample.rest.user.registration.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.PARAMETER })
public @interface LogParam {
	

	String message() default "Parameter";
}
