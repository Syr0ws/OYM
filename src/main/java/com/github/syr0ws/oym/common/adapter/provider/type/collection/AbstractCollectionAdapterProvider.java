package com.github.syr0ws.oym.common.adapter.provider.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;

import java.util.Collection;

public abstract class AbstractCollectionAdapterProvider<E, T extends Collection<E>> implements TypeAdapterProvider<T> {

    protected abstract TypeAdapter<T> getAdapter(TypeAdapter<E> adapter);

    @Override
    @SuppressWarnings("unchecked")
    public TypeAdapter<T> provide(Class<T> type, TypeAdapterFactory factory, Class<?>... generics) {

        if(generics.length != 1)
            throw new IllegalArgumentException("One generic type must be specified.");

        TypeAdapter<E> genericAdapter = (TypeAdapter<E>) factory.getAdapter(generics[0]);

        return this.getAdapter(genericAdapter);
    }
}
