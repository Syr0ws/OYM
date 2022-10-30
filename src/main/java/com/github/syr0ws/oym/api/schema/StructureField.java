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

package com.github.syr0ws.oym.api.schema;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Represents a mappable field of a class. This field is necessarily annotated with @Key.
 */
public class StructureField<T> {

    private final String key;
    private final String[] comments;
    private final Class<T> type;
    private final Field field;
    private final Method getter, setter;

    public StructureField(@NotNull String key, @NotNull String[] comments, @NotNull Class<T> type, @NotNull Field field, Method getter, Method setter) {
        this.key = key;
        this.comments = comments;
        this.type = type;
        this.field = field;
        this.getter = getter;
        this.setter = setter;
    }

    /**
     * @return the name of the field.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the type of the field.
     */
    public Class<T> getType() {
        return this.type;
    }

    /**
     * @return the comments associated with the field.
     */
    public String[] getComments() {
        return this.comments;
    }

    /**
     * @return true if the field has associated comments or else false.
     */
    public boolean hasComments() {
        return this.comments.length != 0;
    }

    /**
     * @return the class field.
     */
    public Field getField() {
        return this.field;
    }

    /**
     * If the field has a getter annotated with @KeyGetter, it can be retrieved using this method. If this
     * optional is not empty, the getter will be used to retrieve the value of the field. Otherwise, the
     * field will be accessed directly.
     * @return an optional.
     */
    public Optional<Method> getGetter() {
        return Optional.ofNullable(this.getter);
    }

    /**
     * If the field has a setter annotated with @KeySetter, it can be retrieved using this method. If this
     * optional is not empty, the setter will be used to assign a value to the field. Otherwise, the field
     * will be accessed directly.
     * @return an optional.
     */
    public Optional<Method> getSetter() {
        return Optional.ofNullable(this.setter);
    }
}
