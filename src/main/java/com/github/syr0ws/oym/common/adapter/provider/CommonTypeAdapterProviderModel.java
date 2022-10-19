package com.github.syr0ws.oym.common.adapter.provider;

import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProviderModel;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommonTypeAdapterProviderModel implements TypeAdapterProviderModel {

    private final Map<Class<?>, TypeAdapterProvider<?>> providers = new HashMap<>();

    @Override
    public void addProvider(@NotNull TypeAdapterProvider<?> provider) {
        this.providers.put(provider.getType(), provider);
    }

    @Override
    public boolean removeAdapter(@NotNull Class<?> type) {
        return this.providers.keySet().removeIf(storedType -> storedType.equals(type));
    }

    @Override
    public boolean hasAdapter(@NotNull Class<?> type) {
        return this.providers.containsKey(type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<TypeAdapterProvider<T>> getAdapter(@NotNull Class<T> type) {
        return this.providers.entrySet().stream()
                .filter(entry -> entry.getKey().equals(type))
                .map(entry -> (TypeAdapterProvider<T>) entry.getValue())
                .findFirst();
    }

    @Override
    public Collection<TypeAdapterProvider<?>> getAdapters() {
        return Collections.unmodifiableCollection(this.providers.values());
    }
}
