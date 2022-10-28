package com.github.syr0ws.oym.common.schema;

import com.github.syr0ws.oym.api.annotation.Key;
import com.github.syr0ws.oym.api.annotation.KeyGetter;
import com.github.syr0ws.oym.api.annotation.KeySetter;
import com.github.syr0ws.oym.api.schema.StructureField;
import com.github.syr0ws.oym.api.schema.StructureSchema;
import com.github.syr0ws.oym.api.schema.StructureSchemaBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

public class CommonStructureSchemaBuilder implements StructureSchemaBuilder {

    @Override
    public <T> StructureSchema<T> build(Class<T> type) {

        Field[] declaredFields = type.getDeclaredFields();

        Set<StructureField<?>> fields = new LinkedHashSet<>();

        for(Field field : declaredFields) {

            if(!field.isAnnotationPresent(Key.class)) continue;

            Key key = field.getAnnotation(Key.class);

            String keyName = key.name().isEmpty() ? field.getName() : key.name();
            Class<?> fieldType = field.getType();
            String[] comments = key.comments();

            Method getter = this.findGetter(type, field);
            Method setter = this.findSetter(type, field);

            StructureField<?> structureField = new StructureField<>(keyName, comments, fieldType, field, getter, setter);

            fields.add(structureField);
        }

        return new StructureSchema<>(type, fields);
    }

    private Method findGetter(Class<?> type, Field field) {

        String name = field.getName();

        for(Method method : type.getDeclaredMethods()) {

            if(!method.isAnnotationPresent(KeyGetter.class)) continue;

            KeyGetter getter = method.getAnnotation(KeyGetter.class);

            if(getter.field().equals(name)) return method;
        }

        return null;
    }

    private Method findSetter(Class<?> type, Field field) {

        String name = field.getName();

        for(Method method : type.getDeclaredMethods()) {

            if(!method.isAnnotationPresent(KeySetter.class)) continue;

            KeySetter getter = method.getAnnotation(KeySetter.class);

            if(getter.field().equals(name)) return method;
        }

        return null;
    }
}
