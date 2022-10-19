package com.github.syr0ws.oym.common.adapter.provider.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.common.adapter.type.collection.HashSetAdapter;

import java.util.HashSet;

public class HashSetAdapterProvider<E> extends AbstractCollectionAdapterProvider<E, HashSet<E>> {

    @Override
    protected TypeAdapter<HashSet<E>> getAdapter(TypeAdapter<E> adapter) {
        return new HashSetAdapter<>(adapter);
    }

    @Override
    public Class<? super HashSet<E>> getType() {
        return HashSet.class;
    }
}
