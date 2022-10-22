package com.github.syr0ws.oym.common.instance;

import com.github.syr0ws.oym.api.instance.InstanceProvider;
import com.github.syr0ws.oym.api.instance.InstanceProviderModel;

import java.util.*;

public class CommonInstanceProviderModel implements InstanceProviderModel {

    private final Map<Class<?>, InstanceProvider<?>> providers = new HashMap<>();

    @Override
    public <T> void addProvider(Class<? super T> type, InstanceProvider<T> provider) {
        this.providers.put(type, provider);
    }

    @Override
    public boolean removeProvider(Class<?> type) {
        return this.providers.keySet().remove(type);
    }

    @Override
    public boolean hasProvider(Class<?> type) {
        return this.providers.containsKey(type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<InstanceProvider<T>> getProvider(Class<? super T> type) {

        InstanceProvider<?> provider = this.providers.getOrDefault(type, null);

        if(provider == null)
            return Optional.empty();

        InstanceProvider<T> casted = null;

        try { casted = (InstanceProvider<T>) provider;
        } catch (ClassCastException exception) { exception.printStackTrace(); }

        return Optional.ofNullable(casted);
    }

    @Override
    public Map<Class<?>, InstanceProvider<?>> getProviders() {
        return Collections.unmodifiableMap(this.providers);
    }
}
