package com.github.syr0ws.oym.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GenericUtil {

    public static List<Class<?>> getGenericTypes(Field field) throws Exception {

        List<Class<?>> genericTypes = new ArrayList<>();

        Type type = field.getGenericType();

        if(type instanceof ParameterizedType) {

            ParameterizedType parameterized = (ParameterizedType) type;

            for(Type parameter : parameterized.getActualTypeArguments()) {

                Class<?> genericType = Class.forName(parameter.getTypeName());

                genericTypes.add(genericType);
            }
        }

        return genericTypes;
    }

    public static Class<?>[] getGenericTypesArray(Field field) throws Exception {

        List<Class<?>> generics = GenericUtil.getGenericTypes(field);
        Class<?>[] array = new Class<?>[generics.size()];

        return generics.toArray(array);
    }

    public static Class<?> getGenericType(Field field) throws Exception {

        Type type = field.getGenericType();

        if(type instanceof ParameterizedType) {

            ParameterizedType parameterized = (ParameterizedType) type;

            Type parameter = parameterized.getActualTypeArguments()[0];

            return Class.forName(parameter.getTypeName());

        } else throw new Exception("Field is not parameterized.");
    }
}
