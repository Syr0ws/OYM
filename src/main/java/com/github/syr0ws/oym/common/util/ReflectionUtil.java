package com.github.syr0ws.oym.common.util;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.schema.StructureField;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

public class ReflectionUtil {

    @SuppressWarnings("unchecked")
    public static <T, V> V getValue(StructureField<V> structureField, T instance) throws Exception {

        Optional<Method> optional = structureField.getGetter();

        Object value;

        if(optional.isPresent()) {

            // Using the setter.
            Method getter = optional.get();
            boolean accessible = getter.isAccessible();

            try {
                getter.setAccessible(true);
                value = getter.invoke(instance);
            } catch (Exception exception) {
                throw new Exception("Cannot use getter to bind a value.", exception);
            } finally {
                getter.setAccessible(accessible);
            }

        } else {

            // Using the field.
            Field field = structureField.getField();
            boolean accessible = field.isAccessible();

            try {
                field.setAccessible(true);
                value = field.get(instance);
            } catch (Exception exception) {
                throw new Exception("Cannot bind a value to a field.", exception);
            } finally {
                field.setAccessible(accessible);
            }
        }

        return (V) value;
    }

    public static <T, V> void setValue(StructureField<V> structureField, T instance, V value) throws Exception {

        Optional<Method> optional = structureField.getSetter();

        if(optional.isPresent()) {

            // Using the setter.
            Method setter = optional.get();
            boolean accessible = setter.isAccessible();

            try {
                setter.setAccessible(true);
                setter.invoke(instance, value);
            } catch (Exception exception) {
                throw new Exception("Cannot use setter to bind a value.", exception);
            } finally {
                setter.setAccessible(accessible);
            }

        } else {

            // Using the field.
            Field field = structureField.getField();
            boolean accessible = field.isAccessible();

            try {
                field.setAccessible(true);
                field.set(instance, value);
            } catch (Exception exception) {
                throw new Exception("Cannot bind a value to a field.", exception);
            } finally {
                field.setAccessible(accessible);
            }
        }
    }
}
