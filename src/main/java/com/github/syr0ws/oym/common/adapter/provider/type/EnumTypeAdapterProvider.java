package com.github.syr0ws.oym.common.adapter.provider.type;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProvider;
import com.github.syr0ws.oym.common.adapter.type.EnumAdapter;

public class EnumTypeAdapterProvider<T extends Enum<T>> implements TypeAdapterProvider<T> {

    @Override
    public TypeAdapter<T> provide(Class<T> type, TypeAdapterFactory factory, Class<?>... generics) {
        return new EnumAdapter<>(type);
    }

    @Override
    public Class<? super T> getType() {
        return Enum.class;
    }
}
