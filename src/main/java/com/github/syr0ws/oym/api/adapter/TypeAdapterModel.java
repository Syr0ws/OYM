package com.github.syr0ws.oym.api.adapter;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface TypeAdapterModel {

    void addAdapter(@NotNull Class<?> type, TypeAdapter<?> adapter);

    boolean removeAdapter(@NotNull Class<?> type);

    boolean hasAdapter(@NotNull Class<?> type);

    <T> TypeAdapter<T> getAdapter(@NotNull Class<T> type);

    Collection<TypeAdapter<?>> getAdapters();
}
