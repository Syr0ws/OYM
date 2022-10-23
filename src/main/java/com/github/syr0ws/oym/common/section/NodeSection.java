package com.github.syr0ws.oym.common.section;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.section.ConfigurationSection;
import com.github.syr0ws.oym.api.section.ConfigurationSectionException;
import org.jetbrains.annotations.NotNull;

public class NodeSection implements ConfigurationSection {

    private final TypeAdapterFactory factory;
    private ObjectNode node;

    public NodeSection(@NotNull TypeAdapterFactory factory, @NotNull ObjectNode node) {
        this.factory = factory;
        this.node = node;
    }

    @Override
    public String getString(@NotNull String path) throws ConfigurationSectionException {
        return this.getValue(path, this.node, String.class);
    }

    @Override
    public String getString(@NotNull String path, String defaultValue) {

        try { return this.getString(path);
        } catch (ConfigurationSectionException ignored) { return defaultValue; }
    }

    @Override
    public boolean getBoolean(@NotNull String path) throws ConfigurationSectionException {
        return this.getValue(path, this.node, Boolean.class);
    }

    @Override
    public boolean getBoolean(@NotNull String path, boolean defaultValue) {

        try { return this.getBoolean(path);
        } catch (ConfigurationSectionException ignored) { return defaultValue; }
    }

    @Override
    public int getInt(@NotNull String path) throws ConfigurationSectionException {
        return this.getValue(path, this.node, Integer.class);
    }

    @Override
    public int getInt(@NotNull String path, int defaultValue) {

        try { return this.getInt(path);
        } catch (ConfigurationSectionException ignored) { return defaultValue; }
    }

    @Override
    public long getLong(@NotNull String path) throws ConfigurationSectionException {
        return this.getValue(path, this.node, Long.class);
    }

    @Override
    public long getLong(@NotNull String path, long defaultValue) {

        try { return this.getLong(path);
        } catch (ConfigurationSectionException ignored) { return defaultValue; }
    }

    @Override
    public float getFloat(@NotNull String path) throws ConfigurationSectionException {
       return this.getValue(path, this.node, Float.class);
    }

    @Override
    public float getFloat(@NotNull String path, float defaultValue) {

        try { return this.getFloat(path);
        } catch (ConfigurationSectionException ignored) { return defaultValue; }
    }

    @Override
    public double getDouble(@NotNull String path) throws ConfigurationSectionException {
        return this.getValue(path, this.node, Double.class);
    }

    @Override
    public double getDouble(@NotNull String path, double defaultValue) {

        try { return this.getDouble(path);
        } catch (ConfigurationSectionException ignored) { return defaultValue; }
    }
    @Override
    public ConfigurationSection getSection(@NotNull String path) throws ConfigurationSectionException {

        ObjectNode parent = this.getParentNode(this.node, new NodePath(path));

        if(parent == null)
            throw new ConfigurationSectionException(String.format("Invalid path '%s'.", path));

        return new NodeSection(this.factory, parent);

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void to(@NotNull T value) throws ConfigurationSectionException {

        TypeAdapter<T> adapter = (TypeAdapter<T>) this.factory.getAdapter(value.getClass());

        try { this.node = (ObjectNode) adapter.write(value);
        } catch (TypeAdaptationException exception) { throw new ConfigurationSectionException(exception); }
    }

    @Override
    public <T> T from(@NotNull Class<T> type) throws ConfigurationSectionException {

        T object;

        TypeAdapter<T> adapter = this.factory.getAdapter(type);

        try { object = adapter.read(this.node);
        } catch (TypeAdaptationException exception) { throw new ConfigurationSectionException(exception); }

        return object;
    }

    @Override
    public boolean hasKey(@NotNull String path) {

        if(this.node.hasProperty(path)) return true;

        NodePath nodePath = new NodePath(path);
        ObjectNode parent = this.getParentNode(this.node, nodePath);

        System.out.println(parent.getProperties());

        return parent != null && parent.hasProperty(nodePath.getProperty());
    }

    @Override
    public ObjectNode getNode() {
        return this.node;
    }

    private ObjectNode getParentNode(ObjectNode parent, NodePath path) {

        if(path.getPath().equals("")) return parent;

        String nextNode = path.getNext();

        // Property not found.
        if(!parent.hasProperty(nextNode)) return null;

        Node subNode = parent.getProperty(nextNode);

        // SubNode is not an ObjectNode.
        if(!subNode.isObject()) return null;

        // Going deeper.
        return this.getParentNode((ObjectNode) subNode, path.getDeeperPath());
    }

    private <T> T getValue(String path, ObjectNode node, Class<T> type) throws ConfigurationSectionException {

        NodePath nodePath = new NodePath(path);

        ObjectNode parent = this.getParentNode(node, nodePath);

        String property = nodePath.getProperty();

        // 1 - Parent node not found which means the path is invalid.
        // 2 - Parent node doesn't contain the property needed.
        if(parent == null || !parent.hasProperty(property))
            throw new ConfigurationSectionException(String.format("Invalid path '%s'.", path));

        Node propertyNode = parent.getProperty(property);

        TypeAdapter<T> adapter = this.factory.getAdapter(type);

        T value;

        try {
            value = adapter.read(propertyNode);
        } catch (TypeAdaptationException exception) {
            throw new ConfigurationSectionException(String.format("Cannot read value at '%s' with type '%s'.", path, type.getName()), exception);
        }

        return value;
    }

    private static class NodePath {

        private final String path, next, property;

        public NodePath(String path) {

            int firstIndex = path.indexOf(".");
            int lastIndex = path.lastIndexOf(".");

            this.path = path; // Current path.
            this.next = firstIndex == -1 ? path : path.substring(0, firstIndex); // Next node.
            this.property = lastIndex == -1 ? path : path.substring(lastIndex+1); // Property.
        }

        // Non-modified path.
        public String getPath() {
            return this.path;
        }

        // Next node.
        public String getNext() {
            return this.next;
        }

        // Path without the next node.
        public NodePath getDeeperPath() {

            String path = this.path.replaceFirst(this.next, "");

            if(path.startsWith(".")) path = path.substring(1);

            return new NodePath(path);
        }

        // The property name.
        public String getProperty() {
            return this.property;
        }
    }
}
