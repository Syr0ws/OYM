package com.github.syr0ws.oym.api.node;

import java.util.Collection;
import java.util.Map;

public class YamlElement extends AbstractNode {

    private final Object object;

    public YamlElement(Object object) {
        this.object = object;
    }

    public String asString() {
        return (String) this.object;
    }

    public int asInt() {
        return (int) this.object;
    }

    public boolean isString() {
        return this.object instanceof String;
    }

    public boolean isInt() {
        return this.object instanceof Integer;
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
