package com.github.syr0ws.oym.common.adapter.provider.type;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.common.adapter.type.BooleanAdapter;

public class BooleanAdapterProvider implements TypeAdapterProvider<Boolean> {

    private static final TypeAdapter<Boolean> BOOLEAN_TYPE_ADAPTER = new BooleanAdapter();

    @Override
    public TypeAdapter<Boolean> provide(Class<Boolean> type, TypeAdapterFactory factory, Class<?>... generics) {
        return BOOLEAN_TYPE_ADAPTER;
    }

    @Override
    public Class<? super Boolean> getType() {
        return Boolean.class;
    }
}
