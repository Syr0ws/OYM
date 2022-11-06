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

package com.github.syr0ws.ofm.common.schema;

import com.github.syr0ws.ofm.api.annotation.Property;
import com.github.syr0ws.ofm.api.annotation.PropertyGetter;
import com.github.syr0ws.ofm.api.annotation.PropertySetter;
import com.github.syr0ws.ofm.api.schema.StructureField;
import com.github.syr0ws.ofm.api.schema.StructureSchema;
import com.github.syr0ws.ofm.api.schema.StructureSchemaBuilder;

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

            // Field must not be used.
            if(!field.isAnnotationPresent(Property.class)) continue;

            Property property = field.getAnnotation(Property.class);

            // Using the field name or a custom name if it is specified.
            String keyName = property.name().isEmpty() ? field.getName() : property.name();
            Class<?> fieldType = field.getType();
            String[] comments = property.comments();

            Method getter = this.findGetter(type, field); // Retrieving the field getter if it exists and is annotated.
            Method setter = this.findSetter(type, field); // Retrieving the field setter if it exists and is annotated.

            StructureField<?> structureField = new StructureField<>(keyName, comments, fieldType, field, getter, setter);

            fields.add(structureField);
        }

        return new StructureSchema<>(type, fields);
    }

    private Method findGetter(Class<?> type, Field field) {

        String name = field.getName();

        for(Method method : type.getDeclaredMethods()) {

            if(!method.isAnnotationPresent(PropertyGetter.class)) continue;

            PropertyGetter getter = method.getAnnotation(PropertyGetter.class);

            if(getter.field().equals(name)) return method;
        }

        return null;
    }

    private Method findSetter(Class<?> type, Field field) {

        String name = field.getName();

        for(Method method : type.getDeclaredMethods()) {

            if(!method.isAnnotationPresent(PropertySetter.class)) continue;

            PropertySetter getter = method.getAnnotation(PropertySetter.class);

            if(getter.field().equals(name)) return method;
        }

        return null;
    }
}
