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
