/*
 *    Copyright 2022 syr0ws
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.syr0ws.ofm.common.util;

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

    public static boolean isPrimitiveWrapper(Class<?> type) {
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
