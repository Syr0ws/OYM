package com.github.syr0ws.oym.api.instance;

import java.util.Map;
import java.util.Optional;

public interface InstanceProviderModel {

    <T> void addProvider(Class<? super T> type, InstanceProvider<T> provider);

    boolean removeProvider(Class<?> type);

    boolean hasProvider(Class<?> type);

    <T> Optional<InstanceProvider<T>> getProvider(Class<? super T> type);

    Map<Class<?>, InstanceProvider<?>> getProviders();
}
