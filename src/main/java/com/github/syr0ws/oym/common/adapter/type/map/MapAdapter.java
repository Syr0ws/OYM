package com.github.syr0ws.oym.common.adapter.type.map;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.common.util.NodeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class MapAdapter<M extends Map<String, V>, V> implements TypeAdapter<M> {

    private final TypeAdapter<V> adapter;

    public MapAdapter(@NotNull TypeAdapter<V> adapter) {
        this.adapter = adapter;
    }

    public abstract M getMapInstance();

    @Override
    public M read(Node node) throws TypeAdaptationException {

        ObjectNode objectNode = NodeUtil.cast(node, ObjectNode.class);
        Map<String, Node> nodes = objectNode.getProperties();

        M map = this.getMapInstance();

        for(Map.Entry<String, Node> entry : nodes.entrySet()) {
            V value = this.adapter.read(entry.getValue());
            map.put(entry.getKey(), value);
        }

        return map;
    }

    @Override
    public Node write(M value) throws TypeAdaptationException {

        ObjectNode object = new ObjectNode(new HashMap<>());

        for(Map.Entry<String, V> entry : value.entrySet()) {

            Node node = this.adapter.write(entry.getValue());

            object.addProperty(entry.getKey(), node);
        }

        return object;
    }
}
