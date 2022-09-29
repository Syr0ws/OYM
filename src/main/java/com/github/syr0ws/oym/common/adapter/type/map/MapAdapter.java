package com.github.syr0ws.oym.common.adapter.type.map;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.api.node.YamlObject;
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
    public M read(YamlNode node) throws TypeAdaptationException {

        YamlObject yamlObject = NodeUtil.cast(node, YamlObject.class);
        Map<String, YamlNode> nodes = yamlObject.getProperties();

        M map = this.getMapInstance();

        for(Map.Entry<String, YamlNode> entry : nodes.entrySet()) {
            V value = this.adapter.read(entry.getValue());
            map.put(entry.getKey(), value);
        }

        return map;
    }

    @Override
    public YamlNode write(M value) throws TypeAdaptationException {

        YamlObject object = new YamlObject(new HashMap<>());

        for(Map.Entry<String, V> entry : value.entrySet()) {

            YamlNode node = this.adapter.write(entry.getValue());

            object.addProperty(entry.getKey(), node);
        }

        return object;
    }
}
