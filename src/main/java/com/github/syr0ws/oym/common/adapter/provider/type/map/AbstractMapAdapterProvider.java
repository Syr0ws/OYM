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

package com.github.syr0ws.oym.common.adapter.provider.type.map;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.TypeAdapterNotFoundException;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;

import java.util.Map;

public abstract class AbstractMapAdapterProvider<V, T extends Map<String, V>> implements TypeAdapterProvider<T> {

    protected abstract TypeAdapter<T> getAdapter(TypeAdapter<V> adapter);

    @Override
    @SuppressWarnings("unchecked")
    public TypeAdapter<T> provide(Class<T> type, TypeAdapterFactory factory, Class<?>... generics) {

        if(generics.length != 2)
            throw new IllegalArgumentException("Two generic types must be specified.");

        TypeAdapter<V> valueAdapter;

        try { valueAdapter = (TypeAdapter<V>) factory.getAdapter(generics[1]);
        } catch (TypeAdapterNotFoundException exception) { throw new RuntimeException(exception); }

        return this.getAdapter(valueAdapter);
    }
}
