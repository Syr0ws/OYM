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
        return null;
    }

    @Override
    public boolean getBoolean(@NotNull String path) throws ConfigurationSectionException {
        return false;
    }

    @Override
    public boolean getBoolean(@NotNull String path, boolean defaultValue) {
        return false;
    }

    @Override
    public int getInt(@NotNull String path) throws ConfigurationSectionException {
        return this.getValue(path, this.node, Integer.class);
    }

    @Override
    public int getInt(@NotNull String path, int defaultValue) {
        return 0;
    }

    @Override
    public long getLong(@NotNull String path) throws ConfigurationSectionException {
        return 0;
    }

    @Override
    public long getLong(@NotNull String path, long defaultValue) {
        return 0;
    }

    @Override
    public float getFloat(@NotNull String path) throws ConfigurationSectionException {
        return 0;
    }

    @Override
    public float getFloat(@NotNull String path, float defaultValue) {
        return 0;
    }

    @Override
    public double getDouble(@NotNull String path) throws ConfigurationSectionException {
        return 0;
    }

    @Override
    public double getDouble(@NotNull String path, double defaultValue) {
        return 0;
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
        NodePath nodePath = new NodePath(path);
        ObjectNode parent = this.getParentNode(this.node, nodePath);
        return parent != null && parent.hasProperty(nodePath.getProperty());
    }

    @Override
    public ObjectNode getNode() {
        return this.node;
    }

    private ObjectNode getParentNode(ObjectNode parent, NodePath path) {

        // p1 -> p1 (avec p1 ObjectNode)
        // p1.p2.p3 -> p2 (avec p2 ObjectNode)

        System.out.println(path.getPath());

        if(path.getPath().equals("")) return parent;

        String nextNode = path.getNextNode();

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

    /*
    private <T> T getValue(String path, ObjectNode node, Class<T> type) throws ConfigurationSectionException {

        T value;

        if(!path.contains(".")) {

            if(!node.hasProperty(path))
                throw new ConfigurationSectionException(String.format("No key found in '%s'.", path));

            Node property = node.getProperty(path);

            TypeAdapter<T> adapter = this.factory.getAdapter(type);

            try {
                value = adapter.read(property);
            } catch (TypeAdaptationException exception) {
                throw new ConfigurationSectionException(String.format("Cannot read value at '%s' with type '%s'.", path, type.getName()), exception);
            }

        } else {

            String subPath = path.split("\\.")[0];

            if(!node.hasProperty(subPath))
                throw new ConfigurationSectionException(String.format("Key '%s' not found in '%s'.", subPath, path));

            Node property = node.getProperty(subPath);

            if(!property.isObject())
                throw new ConfigurationSectionException(String.format("Key '%s' is not a sub section in '%s'.", subPath, path));

            path = path.replaceFirst(subPath + "\\.", "");

            value = this.getValue(path, (ObjectNode) property, type);
        }

        return value;
    }
     */

    private class NodePath {

        private final String path, nodes, nextNode, property;

        public NodePath(String path) {

            int firstIndex = path.indexOf(".");
            int lastIndex = path.lastIndexOf(".");

            this.path = path;
            this.nodes = lastIndex == -1 ? null : path.substring(0, lastIndex);
            this.property = lastIndex == -1 ? path : path.substring(lastIndex+1);
            this.nextNode = firstIndex == -1 || this.nodes == null ? path : this.nodes.substring(0, firstIndex);
        }

        // Non-modified path.
        public String getPath() {
            return this.path;
        }

        // Path only composed of nodes, without the property name.
        public String getNodes() {
            return this.nodes;
        }

        public String getNextNode() {
            return this.nextNode;
        }

        // Path only composed of nodes, without the next node.
        public NodePath getDeeperPath() {
            String path = this.path.replaceFirst(this.nextNode + "\\.", "");
            System.out.println("replaced " + path);
            return new NodePath(path);
        }

        // The property name.
        public String getProperty() {
            return this.property;
        }
    }
}
