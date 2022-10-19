package com.github.syr0ws.oym.common.adapter.provider.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.common.adapter.type.collection.SetAdapter;

import java.util.Set;

public class SetAdapterProvider<E> extends AbstractCollectionAdapterProvider<E, Set<E>> {

    @Override
    protected TypeAdapter<Set<E>> getAdapter(TypeAdapter<E> adapter) {
        return new SetAdapter<>(adapter);
    }

    @Override
    public Class<? super Set<E>> getType() {
        return Set.class;
    }
}
