/*
 *    Copyright 2022 syr0ws
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.syr0ws.ofm.common.adapter.provider;

import com.github.syr0ws.ofm.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.ofm.api.adapter.provider.TypeAdapterProviderModel;
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
