package com.github.syr0ws.oym.common.adapter.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionAdapter<E> extends AbstractCollectionAdapter<Collection<E>, E> {

    public CollectionAdapter(@NotNull TypeAdapter<E> adapter) {
        super(adapter);
    }

    @Override
    public Collection<E> getInstance() {
        return new ArrayList<>();
    }
}
