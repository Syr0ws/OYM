package com.github.syr0ws.oym.api.node;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;

public class YamlObject implements YamlNode {

    private final Map<String, YamlNode> nodes;

    public YamlObject(Map<String, YamlNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    public void addProperty(@NotNull String key, @NotNull YamlNode node) {
        this.nodes.put(key, node);
    }

    public boolean removeProperty(@NotNull String key) {
        return this.nodes.remove(key) != null;
    }

    public boolean hasProperty(@NotNull String key) {
        return this.nodes.containsKey(key);
    }

    public YamlNode getProperty(@NotNull String key) {
        return this.nodes.get(key);
    }

    public Map<String, YamlNode> getProperties() {
        return Collections.unmodifiableMap(this.nodes);
    }
}
