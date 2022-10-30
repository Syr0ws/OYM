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

package com.github.syr0ws.ofm.common.adapter.provider.type.collection;

import com.github.syr0ws.ofm.api.adapter.TypeAdapter;
import com.github.syr0ws.ofm.common.adapter.type.collection.HashSetAdapter;

import java.util.HashSet;

public class HashSetAdapterProvider<E> extends AbstractCollectionAdapterProvider<E, HashSet<E>> {

    @Override
    protected TypeAdapter<HashSet<E>> getAdapter(TypeAdapter<E> adapter) {
        return new HashSetAdapter<>(adapter);
    }

    @Override
    public Class<? super HashSet<E>> getType() {
        return HashSet.class;
    }
}
