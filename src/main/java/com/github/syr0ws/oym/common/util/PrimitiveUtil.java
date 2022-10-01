package com.github.syr0ws.oym.common.util;

import java.util.HashMap;
import java.util.Map;

public class PrimitiveUtil {

    private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS = new HashMap<>();

    static {
        PRIMITIVES_TO_WRAPPERS.put(boolean.class, Boolean.class);
        PRIMITIVES_TO_WRAPPERS.put(byte.class, Byte.class);
        PRIMITIVES_TO_WRAPPERS.put(short.class, Short.class);
        PRIMITIVES_TO_WRAPPERS.put(char.class, Character.class);
        PRIMITIVES_TO_WRAPPERS.put(double.class, Double.class);
        PRIMITIVES_TO_WRAPPERS.put(float.class, Float.class);
        PRIMITIVES_TO_WRAPPERS.put(int.class, Integer.class);
        PRIMITIVES_TO_WRAPPERS.put(long.class, Long.class);
    }

    @SuppressWarnings("unchecked")
   public static <T> Class<T> getWrapper(Class<T> primitiveType) {
        return primitiveType.isPrimitive() ? (Class<T>) PRIMITIVES_TO_WRAPPERS.get(primitiveType) : primitiveType;
    }
}
