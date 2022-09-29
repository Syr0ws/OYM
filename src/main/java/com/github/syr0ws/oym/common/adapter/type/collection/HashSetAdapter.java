package com.github.syr0ws.oym.common.adapter.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class HashSetAdapter<E> extends CollectionAdapter<HashSet<E>, E> {

    public HashSetAdapter(@NotNull TypeAdapter<E> adapter) {
        super(adapter);
    }

    @Override
    public HashSet<E> getInstance() {
        return new HashSet<>();
    }
}
