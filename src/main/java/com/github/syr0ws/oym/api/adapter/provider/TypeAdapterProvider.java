package com.github.syr0ws.oym.api.adapter.provider;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;

public interface TypeAdapterProvider<T> {

    TypeAdapter<T> provide(Class<T> type, TypeAdapterFactory factory, Class<?>... generics);

    Class<? super T> getType();
}
