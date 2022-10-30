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

package com.github.syr0ws.oym.common.adapter.provider.type;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.common.adapter.type.UUIDAdapter;

import java.util.UUID;

public class UUIDAdapterProvider implements TypeAdapterProvider<UUID> {

    private static final TypeAdapter<UUID> UUID_TYPE_ADAPTER = new UUIDAdapter();

    @Override
    public TypeAdapter<UUID> provide(Class<UUID> type, TypeAdapterFactory factory, Class<?>... generics) {
        return UUID_TYPE_ADAPTER;
    }

    @Override
    public Class<? super UUID> getType() {
        return UUID.class;
    }
}
