package com.github.syr0ws.oym.common.adapter.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.YamlCollection;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.common.util.NodeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CollectionAdapter<T extends Collection<E>, E> implements TypeAdapter<T> {

    private final TypeAdapter<E> adapter;

    public CollectionAdapter(@NotNull TypeAdapter<E> adapter) {
        this.adapter = adapter;
    }

    public abstract T getInstance();

    @Override
    public T read(YamlNode node) throws TypeAdaptationException {

        YamlCollection yamlCollection = NodeUtil.cast(node, YamlCollection.class);

        T collection = this.getInstance();

        for(YamlNode internal : yamlCollection) {

            E object = this.adapter.read(internal);

            collection.add(object);
        }

        return collection;
    }

    @Override
    public YamlNode write(T collection) throws TypeAdaptationException {

        List<YamlNode> nodes = new ArrayList<>();

        for(E object : collection) {

            YamlNode node = this.adapter.write(object);

            nodes.add(node);
        }

        return new YamlCollection(nodes);
    }
}
