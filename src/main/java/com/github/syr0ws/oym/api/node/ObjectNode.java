package com.github.syr0ws.oym.api.node;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an object that is not scalar nor a collection.
 */
public class ObjectNode extends AbstractNode implements Node {

    private final Map<String, Node> nodes;

    public ObjectNode() {
        this.nodes = new LinkedHashMap<>();
    }

    public ObjectNode(Map<String, Node> nodes) {
        this();
        this.nodes.putAll(nodes);
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public Object get() {
        return this.getProperties();
    }

    public void addProperty(@NotNull String key, @NotNull Node node) {
        this.nodes.put(key, node);
    }

    public boolean removeProperty(@NotNull String key) {
        return this.nodes.remove(key) != null;
    }

    public boolean hasProperty(@NotNull String key) {
        return this.nodes.containsKey(key);
    }

    public Node getProperty(@NotNull String key) {
        return this.nodes.get(key);
    }

    public Map<String, Node> getProperties() {
        return Collections.unmodifiableMap(this.nodes);
    }
}
