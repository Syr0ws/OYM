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

package com.github.syr0ws.ofm.common.adapter.provider.type;

import com.github.syr0ws.ofm.api.adapter.TypeAdapter;
import com.github.syr0ws.ofm.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.ofm.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.ofm.common.adapter.type.IntegerAdapter;

public class IntegerAdapterProvider implements TypeAdapterProvider<Integer> {

    private static final TypeAdapter<Integer> INTEGER_TYPE_ADAPTER = new IntegerAdapter();

    @Override
    public TypeAdapter<Integer> provide(Class<Integer> type, TypeAdapterFactory factory, Class<?>... generics) {
        return INTEGER_TYPE_ADAPTER;
    }

    @Override
    public Class<? super Integer> getType() {
        return Integer.class;
    }
}
