package com.github.syr0ws.oym.api.instance;

import java.util.Optional;
import java.util.Set;

public interface InstanceProviderModel {

    void addProvider(InstanceProvider<?> provider);

    boolean removeProvider(Class<?> type);

    boolean hasProvider(Class<?> type);

    <I> Optional<InstanceProvider<I>> getProvider(Class<I> type);

    Set<InstanceProvider<?>> getProviders();
}
