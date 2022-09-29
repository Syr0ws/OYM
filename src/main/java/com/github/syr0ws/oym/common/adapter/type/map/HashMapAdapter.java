package com.github.syr0ws.oym.common.adapter.type.map;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class HashMapAdapter<V> extends MapAdapter<HashMap<String, V>, V> {

    public HashMapAdapter(@NotNull TypeAdapter<V> adapter) {
        super(adapter);
    }

    @Override
    public HashMap<String, V> getMapInstance() {
        return new HashMap<>();
    }
}
