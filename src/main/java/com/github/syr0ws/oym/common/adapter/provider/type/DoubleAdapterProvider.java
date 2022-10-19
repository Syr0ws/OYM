package com.github.syr0ws.oym.common.adapter.provider.type;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.common.adapter.type.DoubleAdapter;

public class DoubleAdapterProvider implements TypeAdapterProvider<Double> {

    private static final TypeAdapter<Double> DOUBLE_TYPE_ADAPTER = new DoubleAdapter();

    @Override
    public TypeAdapter<Double> provide(Class<Double> type, TypeAdapterFactory factory, Class<?>... generics) {
        return DOUBLE_TYPE_ADAPTER;
    }

    @Override
    public Class<? super Double> getType() {
        return Double.class;
    }
}
