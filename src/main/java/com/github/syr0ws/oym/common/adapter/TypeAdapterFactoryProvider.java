package com.github.syr0ws.oym.common.adapter;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
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
    public <T> TypeAdapter<T> getAdapter(Class<T> type, Class<?>... generics) {

        Optional<? extends TypeAdapterProvider<?>> optional;

        Class<?> unknownType = type;

        // Instructions specific to the type.
        if(PrimitiveUtil.isPrimitive(type)) {

            unknownType = PrimitiveUtil.getWrapper(type);

        } else if(TypeUtil.isEnum(type)) {

            unknownType = Enum.class;
        }

        // Retrieving the corresponding TypeAdapterProvider.
        optional = this.typeAdapterProviderModel.getAdapter(unknownType);

        // No specific TypeAdapterProvider found.
        if(!optional.isPresent())
            return new ObjectAdapter<>(this.schemaBuilder, this.instanceProviderService, this, type);

        TypeAdapterProvider<T> provider = (TypeAdapterProvider<T>) optional.get();

        return provider.provide(type, this, generics);
    }
}
