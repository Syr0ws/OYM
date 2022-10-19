package com.github.syr0ws.oym.api.node;

import java.util.Collection;
import java.util.Map;

public class ScalarNode extends AbstractNode {

    private final Object object;

    public ScalarNode(Object object) {
        this.object = object;
    }

    public String asString() {
        return (String) this.object;
    }

    public boolean asBoolean() {
        return (boolean) this.object;
    }

    public int asInt() {
        return (int) this.object;
    }

    public double asDouble() {
        return (double) this.object;
    }

    public long asLong() {
        return (long) this.object;
    }

    public boolean isString() {
        return this.object instanceof String;
    }

    public boolean isBoolean() {
        return this.object instanceof Boolean;
    }

    public boolean isInt() {
        return this.object instanceof Integer;
    }

    public boolean isDouble() {
        return this.object instanceof Double;
    }

    public boolean isLong() {
        return this.object instanceof Long;
    }

    public Object get() {
        return this.object;
    }

    @Override
    public boolean isObject() {
        return this.object instanceof Map;
    }

    @Override
    public boolean isCollection() {
        return this.object instanceof Collection;
    }
}