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
