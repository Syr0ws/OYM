package com.github.syr0ws.oym.common.adapter.provider.type;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.common.adapter.type.LongAdapter;

public class LongAdapterProvider implements TypeAdapterProvider<Long> {

    private static final TypeAdapter<Long> LONG_TYPE_ADAPTER = new LongAdapter();

    @Override
    public TypeAdapter<Long> provide(Class<Long> type, TypeAdapterFactory factory, Class<?>... generics) {
        return LONG_TYPE_ADAPTER;
    }

    @Override
    public Class<? super Long> getType() {
        return Long.class;
    }
}
