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

package com.github.syr0ws.ofm.api.adapter.provider;

import com.github.syr0ws.ofm.api.adapter.TypeAdapter;
import com.github.syr0ws.ofm.api.adapter.TypeAdapterFactory;

public interface TypeAdapterProvider<T> {

    /**
     * Instantiate a TypeAdapter which is able to manipulate objects of type passed as parameter.
     * @param type the type to be manipulated by the TypeAdapter instance.
     * @param factory a TypeAdapterFactory instance. It is especially used to retrieve adapters for generic types.
     * @param generics If the type to manipulate has generic parameters, they must be specified in this field.
     * @return the corresponding TypeAdapter instance.
     */
    TypeAdapter<T> provide(Class<T> type, TypeAdapterFactory factory, Class<?>... generics);

    /**
     * Get the object type manipulated by the provider.
     * @return an object type.
     */
    Class<? super T> getType();
}
