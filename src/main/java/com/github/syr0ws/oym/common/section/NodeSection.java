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

        ObjectNode parent = this.getParentNode(this.node, path);

        if (parent == null)
            throw new ConfigurationSectionException(String.format("Invalid path '%s'.", path));

        String key = this.getKey(path);

        return new NodeSection(this.factory, (ObjectNode) parent.getProperty(key));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void to(@NotNull T value) throws ConfigurationSectionException {

        TypeAdapter<T> adapter = (TypeAdapter<T>) this.factory.getAdapter(value.getClass());

        try {
            this.node = (ObjectNode) adapter.write(value);
        } catch (TypeAdaptationException exception) {
            throw new ConfigurationSectionException(exception);
        }
    }

    @Override
    public <T> T from(@NotNull Class<T> type) throws ConfigurationSectionException {

        T object;

        TypeAdapter<T> adapter = this.factory.getAdapter(type);

        try {
            object = adapter.read(this.node);
        } catch (TypeAdaptationException exception) {
            throw new ConfigurationSectionException(exception);
        }

        return object;
    }

    @Override
    public boolean hasKey(@NotNull String path) {
        ObjectNode parent = this.getParentNode(this.node, path);
        String key = this.getKey(path);
        return parent != null && parent.hasProperty(key);
    }

    @Override
    public ObjectNode getNode() {
        return this.node;
    }

    private ObjectNode getParentNode(ObjectNode parent, String path) {

        int index = path.indexOf(".");

        // Path is a leaf.
        if(index == -1) return parent.hasProperty(path) ? parent : null;

        // Path contains a dot and has a next node.
        String nextNodeKey = path.substring(0, index);

        // Next property not found in the tree.
        if (!parent.hasProperty(nextNodeKey)) return null;

        Node nextNode = parent.getProperty(nextNodeKey);

        // Next key is not a node.
        if (!nextNode.isObject()) return null;

        // If path ends with a dot.
        if (index + 1 >= path.length()) return null;

        String deeperPath = path.substring(index + 1);

        return this.getParentNode((ObjectNode) nextNode, deeperPath);
    }

    private <T> T getValue(String path, ObjectNode node, Class<T> type) throws ConfigurationSectionException {

        ObjectNode parent = this.getParentNode(node, path);

        if (parent == null)
            throw new ConfigurationSectionException(String.format("Path '%s' not found.", path));

        int index = path.lastIndexOf(".");
        String leaf = path.substring(index + 1);

        Node propertyNode = parent.getProperty(leaf);

        TypeAdapter<T> adapter = this.factory.getAdapter(type);

        T value;

        try {
            value = adapter.read(propertyNode);
        } catch (TypeAdaptationException exception) {
            throw new ConfigurationSectionException(String.format("Cannot access value at '%s' using type '%s'.", path, type.getName()), exception);
        }

        return value;
    }

    private String getKey(String path) {
        return path.contains(".") ? path.substring(path.lastIndexOf(".")+1) : path;
    }
}
