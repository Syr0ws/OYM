package com.github.syr0ws.oym.common.adapter.provider.type;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.common.adapter.type.StringAdapter;

public class StringAdapterProvider implements TypeAdapterProvider<String> {

    private static final TypeAdapter<String> STRING_TYPE_ADAPTER = new StringAdapter();

    @Override
    public TypeAdapter<String> provide(Class<String> type, TypeAdapterFactory factory, Class<?>... generics) {
        return STRING_TYPE_ADAPTER;
    }

    @Override
    public Class<? super String> getType() {
        return String.class;
    }
}
