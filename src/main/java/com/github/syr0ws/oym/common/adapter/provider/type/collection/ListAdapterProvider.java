package com.github.syr0ws.oym.common.adapter.provider.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.common.adapter.type.collection.ListAdapter;

import java.util.List;

public class ListAdapterProvider<E> extends AbstractCollectionAdapterProvider<E, List<E>> {

    @Override
    protected TypeAdapter<List<E>> getAdapter(TypeAdapter<E> adapter) {
        return new ListAdapter<>(adapter);
    }

    @Override
    public Class<? super List<E>> getType() {
        return List.class;
    }
}
