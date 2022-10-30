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

package com.github.syr0ws.oym.common.adapter.provider.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.TypeAdapterNotFoundException;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;

import java.util.Collection;

public abstract class AbstractCollectionAdapterProvider<E, T extends Collection<E>> implements TypeAdapterProvider<T> {

    protected abstract TypeAdapter<T> getAdapter(TypeAdapter<E> adapter);

    @Override
    @SuppressWarnings("unchecked")
    public TypeAdapter<T> provide(Class<T> type, TypeAdapterFactory factory, Class<?>... generics) {

        if(generics.length != 1)
            throw new IllegalArgumentException("One generic type must be specified.");

        TypeAdapter<E> genericAdapter;

        try { genericAdapter = (TypeAdapter<E>) factory.getAdapter(generics[0]);
        } catch (TypeAdapterNotFoundException e) { throw new RuntimeException(e); }

        return this.getAdapter(genericAdapter);
    }
}
