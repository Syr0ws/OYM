package com.github.syr0ws.oym.common.adapter.provider.type;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.common.adapter.type.IntegerAdapter;

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
