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

package com.github.syr0ws.oym.common.adapter;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.TypeAdapterNotFoundException;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProviderModel;
import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import com.github.syr0ws.oym.api.schema.StructureSchemaBuilder;
import com.github.syr0ws.oym.common.adapter.type.ObjectAdapter;
import com.github.syr0ws.oym.common.util.PrimitiveUtil;
import com.github.syr0ws.oym.common.util.TypeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TypeAdapterFactoryProvider implements TypeAdapterFactory {

    private final TypeAdapterProviderModel typeAdapterProviderModel;
    private final InstanceProviderService instanceProviderService;
    private final StructureSchemaBuilder schemaBuilder;

    public TypeAdapterFactoryProvider(@NotNull TypeAdapterProviderModel typeAdapterProviderModel, @NotNull InstanceProviderService instanceProviderService, @NotNull StructureSchemaBuilder schemaBuilder) {
        this.typeAdapterProviderModel = typeAdapterProviderModel;
        this.instanceProviderService = instanceProviderService;
        this.schemaBuilder = schemaBuilder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> getAdapter(Class<T> type, Class<?>... generics) throws TypeAdapterNotFoundException {

        Optional<? extends TypeAdapterProvider<?>> optional;

        Class<?> unknownType = type;
        boolean primitive = PrimitiveUtil.isPrimitive(type);

        // Instructions specific to the type.
        if(primitive) {

            unknownType = PrimitiveUtil.getWrapper(type);

        } else if(TypeUtil.isEnum(type)) {

            unknownType = Enum.class;
        }

        // Retrieving the corresponding TypeAdapterProvider.
        optional = this.typeAdapterProviderModel.getAdapter(unknownType);

        // No specific TypeAdapterProvider found.
        if(!optional.isPresent()) {

            if(primitive)
                throw new TypeAdapterNotFoundException(String.format("Primitive '%s' not supported.", type.getSimpleName()));

            return new ObjectAdapter<>(this.schemaBuilder, this.instanceProviderService, this, type);
        }

        TypeAdapterProvider<T> provider = (TypeAdapterProvider<T>) optional.get();

        return provider.provide(type, this, generics);
    }
}
