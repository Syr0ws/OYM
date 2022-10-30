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

package com.github.syr0ws.ofm.api.adapter;

public interface TypeAdapterFactory {

    /**
     * Get an instance of TypeAdapter to manipulate (read, write) a specific object type.
     * @param type The type for which get an instance of TypeAdapter.
     * @param generics If the type to manipulate has generic parameters, they must be specified in this field.
     * @return an instance of TypeAdapter to manipulate the specified type.
     * @throws TypeAdapterNotFoundException if no TypeAdapter was found. This is especially
     * the case for primitives like byte, short and float.
     */
    <T> TypeAdapter<T> getAdapter(Class<T> type, Class<?>... generics) throws TypeAdapterNotFoundException;
}
