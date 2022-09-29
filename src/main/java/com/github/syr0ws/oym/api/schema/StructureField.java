package com.github.syr0ws.oym.api.schema;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

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

    public String getKey() {
        return this.key;
    }

    public Class<T> getType() {
        return this.type;
    }

    public String[] getComments() {
        return this.comments;
    }

    public boolean hasComments() {
        return this.comments.length != 0;
    }

    public Field getField() {
        return this.field;
    }

    public Optional<Method> getGetter() {
        return Optional.ofNullable(this.getter);
    }

    public Optional<Method> getSetter() {
        return Optional.ofNullable(this.setter);
    }
}
