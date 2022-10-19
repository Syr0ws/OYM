package com.github.syr0ws.oym.common.adapter.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class SetAdapter<E> extends AbstractCollectionAdapter<Set<E>, E> {

    public SetAdapter(@NotNull TypeAdapter<E> adapter) {
        super(adapter);
    }

    @Override
    public Set<E> getInstance() {
        return new HashSet<>();
    }
}
