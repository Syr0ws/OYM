package com.github.syr0ws.oym.common.adapter;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterModel;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TypeAdapterModelProvider implements TypeAdapterModel {

    private final Map<Class<?>, TypeAdapter<?>> adapters = new HashMap<>();

    @Override
    public void addAdapter(@NotNull Class<?> type, TypeAdapter<?> adapter) {
        this.adapters.put(type, adapter);
    }

    @Override
    public boolean removeAdapter(@NotNull Class<?> type) {
        return this.adapters.remove(type) != null;
    }

    @Override
    public boolean hasAdapter(@NotNull Class<?> type) {
        return this.adapters.containsKey(type);
    }

    @Override
    public <T> TypeAdapter<T> getAdapter(@NotNull Class<T> type) {

        if(!this.hasAdapter(type))
            throw new NullPointerException(String.format("No TypeAdapter found for type '%s'.", type.getName()));

        TypeAdapter<?> adapter = this.adapters.get(type);

        @SuppressWarnings("unchecked")
        TypeAdapter<T> casted = (TypeAdapter<T>) adapter;

        return casted;
    }

    @Override
    public Collection<TypeAdapter<?>> getAdapters() {
        return Collections.unmodifiableCollection(this.adapters.values());
    }
}
