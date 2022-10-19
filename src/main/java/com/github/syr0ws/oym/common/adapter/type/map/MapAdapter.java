package com.github.syr0ws.oym.common.adapter.type.map;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MapAdapter<V> extends AbstractMapAdapter<Map<String, V>, V> {

    public MapAdapter(@NotNull TypeAdapter<V> adapter) {
        super(adapter);
    }

    @Override
    public Map<String, V> getMapInstance() {
        return new HashMap<>();
    }
}
