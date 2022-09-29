package com.github.syr0ws.oym.api.adapter;

public interface TypeAdapterFactory {

    <T> TypeAdapter<T> getAdapter(Class<T> type, Class<?>... generics);
}
