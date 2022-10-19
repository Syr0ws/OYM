package com.github.syr0ws.oym.common.adapter.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter<E> extends AbstractCollectionAdapter<List<E>, E> {

    public ListAdapter(@NotNull TypeAdapter<E> adapter) {
        super(adapter);
    }

    @Override
    public List<E> getInstance() {
        return new ArrayList<>();
    }
}
