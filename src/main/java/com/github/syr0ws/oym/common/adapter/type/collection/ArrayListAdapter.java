package com.github.syr0ws.oym.common.adapter.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ArrayListAdapter<E> extends AbstractCollectionAdapter<ArrayList<E>, E> {

    public ArrayListAdapter(@NotNull TypeAdapter<E> adapter) {
        super(adapter);
    }

    @Override
    public ArrayList<E> getInstance() {
        return new ArrayList<>();
    }
}
