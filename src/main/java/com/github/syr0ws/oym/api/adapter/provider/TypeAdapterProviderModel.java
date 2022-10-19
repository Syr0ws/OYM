package com.github.syr0ws.oym.api.adapter.provider;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public interface TypeAdapterProviderModel {

    void addProvider(@NotNull TypeAdapterProvider<?> provider);

    boolean removeAdapter(@NotNull Class<?> type);

    boolean hasAdapter(@NotNull Class<?> type);

    <T> Optional<TypeAdapterProvider<T>> getAdapter(@NotNull Class<T> type);

    Collection<TypeAdapterProvider<?>> getAdapters();
}
