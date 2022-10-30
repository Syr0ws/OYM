package com.github.syr0ws.oym.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Set a method of a class as a setter to be used to change the value of a specific field.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KeySetter {

    /**
     * The name of the corresponding field in the class.
     * @return a field name.
     */
    String field();
}
