package com.github.syr0ws.oym.common.section;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.section.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

public class NodeSection implements ConfigurationSection {

    private final TypeAdapterFactory factory;
    private ObjectNode node;

    public NodeSection(@NotNull TypeAdapterFactory factory, @NotNull ObjectNode node) {
        this.factory = factory;
        this.node = node;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void to(T value) {

        TypeAdapter<T> adapter = (TypeAdapter<T>) this.factory.getAdapter(value.getClass());

        try { this.node = (ObjectNode) adapter.write(value);
        } catch (TypeAdaptationException exception) { exception.printStackTrace(); }
    }

    @Override
    public <T> T from(Class<T> type) {

        T object = null;
        TypeAdapter<T> adapter = this.factory.getAdapter(type);

        try { object = adapter.read(this.node);
        } catch (TypeAdaptationException exception) { exception.printStackTrace(); }

        return object;
    }

    @Override
    public ObjectNode getNode() {
        return this.node;
    }
}
