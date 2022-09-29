package com.github.syr0ws.oym.common.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TypeUtil {

    private static final List<Class<?>> PRIMITIVE_TYPES = Arrays.asList(
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Double.class,
            Float.class,
            Boolean.class,
            String.class
    );

    public static boolean isPrimitive(Class<?> type) {
        return PRIMITIVE_TYPES.contains(type);
    }

    public static boolean isEnum(Class<?> type) {
        return type.isEnum();
    }

    public static boolean isCollection(Object object) {
        return object instanceof Collection;
    }

    public static boolean isMap(Object object) {
        return object instanceof Map;
    }
}
