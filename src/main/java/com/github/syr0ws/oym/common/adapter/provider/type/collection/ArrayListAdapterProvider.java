package com.github.syr0ws.oym.common.adapter.provider.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.common.adapter.type.collection.ArrayListAdapter;

import java.util.ArrayList;

public class ArrayListAdapterProvider<E> extends AbstractCollectionAdapterProvider<E, ArrayList<E>> {

    @Override
    protected TypeAdapter<ArrayList<E>> getAdapter(TypeAdapter<E> adapter) {
        return new ArrayListAdapter<>(adapter);
    }

    @Override
    public Class<? super ArrayList<E>> getType() {
        return ArrayList.class;
    }
}
