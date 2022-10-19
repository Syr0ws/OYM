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
