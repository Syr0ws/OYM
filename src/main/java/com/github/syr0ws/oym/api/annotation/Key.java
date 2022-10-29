package com.github.syr0ws.oym.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Set a field of a class to be handled when serializing or deserializing
 * an object from a file.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Key {

    /**
     * Set a custom name for the annotated field which will be used as key when serializing
     * and deserializing the object. If empty, the field name will be used.
     * @return a name to be used for the field.
     */
    String name() default "";

    /**
     * Set comments to be added with the field when serializing the object.
     * @return an array of comments. Each value of the array represents a single line.
     */
    String[] comments() default {};
}
