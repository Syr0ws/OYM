package com.github.syr0ws.oym.common.adapter.provider.type.map;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.common.adapter.type.map.HashMapAdapter;

import java.util.HashMap;

public class HashMapAdapterProvider<V> extends AbstractMapAdapterProvider<V, HashMap<String, V>> {

    @Override
    protected TypeAdapter<HashMap<String, V>> getAdapter(TypeAdapter<V> adapter) {
        return new HashMapAdapter<>(adapter);
    }

    @Override
    public Class<? super HashMap<String, V>> getType() {
        return HashMap.class;
    }
}
