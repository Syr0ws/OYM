package com.github.syr0ws.oym.api.instance;

import java.util.Map;
import java.util.Optional;

/**
 * Stores all the InstanceProvider which can be used during the mapping process.
 */
public interface InstanceProviderModel {

    <T> void addProvider(Class<? super T> type, InstanceProvider<T> provider);

    boolean removeProvider(Class<?> type);

    boolean hasProvider(Class<?> type);

    <T> Optional<InstanceProvider<T>> getProvider(Class<? super T> type);

    Map<Class<?>, InstanceProvider<?>> getProviders();
}
