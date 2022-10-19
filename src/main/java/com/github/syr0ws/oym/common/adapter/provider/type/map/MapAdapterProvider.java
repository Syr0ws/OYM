package com.github.syr0ws.oym.common.adapter.provider.type.map;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.common.adapter.type.map.MapAdapter;

import java.util.Map;

public class MapAdapterProvider<V> extends AbstractMapAdapterProvider<V, Map<String, V>> {

    @Override
    protected TypeAdapter<Map<String, V>> getAdapter(TypeAdapter<V> adapter) {
        return new MapAdapter<>(adapter);
    }

    @Override
    public Class<? super Map<String, V>> getType() {
        return Map.class;
    }
}
