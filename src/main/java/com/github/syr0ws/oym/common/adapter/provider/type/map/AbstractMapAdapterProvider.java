package com.github.syr0ws.oym.common.adapter.provider.type.map;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;

import java.util.Map;

public abstract class AbstractMapAdapterProvider<V, T extends Map<String, V>> implements TypeAdapterProvider<T> {

    protected abstract TypeAdapter<T> getAdapter(TypeAdapter<V> adapter);

    @Override
    @SuppressWarnings("unchecked")
    public TypeAdapter<T> provide(Class<T> type, TypeAdapterFactory factory, Class<?>... generics) {

        if(generics.length != 2)
            throw new IllegalArgumentException("Two generic types must be specified.");

        TypeAdapter<V> valueAdapter = (TypeAdapter<V>) factory.getAdapter(generics[1]);

        return this.getAdapter(valueAdapter);
    }
}
