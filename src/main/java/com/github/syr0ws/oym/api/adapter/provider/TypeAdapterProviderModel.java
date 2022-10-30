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

package com.github.syr0ws.oym.api.adapter.provider;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

/**
 * Stores all the providers which can be used during the mapping process.
 */
public interface TypeAdapterProviderModel {

    /**
     * Register a new TypeAdapterProvider instance to the model. If the type
     * manipulated by the instance already exists, it is overridden by the new one.
     * @param provider an instance of TypeAdapterProvider.
     */
    void addProvider(@NotNull TypeAdapterProvider<?> provider);

    /**
     * Unregister a TypeAdapterProvider instance from the model.
     * @param type an object type manipulated by the instance to be removed.
     * @return true if a corresponding instance exists and has been removed or else false.
     */
    boolean removeAdapter(@NotNull Class<?> type);

    /**
     * Check if a TypeAdapterProvider instance that manipulates a specific object type is registered.
     * @param type an object type.
     * @return true if a corresponding instance exists or else false.
     */
    boolean hasAdapter(@NotNull Class<?> type);

    /**
     * Get an Optional that contains a TypeAdapterProvider instance that manipulates a specific object type.
     * @param type an object type.
     * @return a non-empty optional if an instance of the class TypeAdapterProvider that manipulates
     * the specified object type is registered or else an empty optional.
     */
    <T> Optional<TypeAdapterProvider<T>> getAdapter(@NotNull Class<T> type);

    /**
     * Get all the registered TypeAdapterProvider instances.
     * @return a collection of TypeAdapterProvider objects.
     */
    Collection<TypeAdapterProvider<?>> getAdapters();
}
