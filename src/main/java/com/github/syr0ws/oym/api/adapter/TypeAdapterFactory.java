package com.github.syr0ws.oym.api.adapter;

public interface TypeAdapterFactory {

    /**
     * Get an instance of TypeAdapter to manipulate (read, write) a specific object type.
     * @param type The type for which get an instance of TypeAdapter.
     * @param generics If the type to manipulate has generic parameters, they must be specified in this field.
     * @return an instance of TypeAdapter to manipulate the specified type.
     * @throws TypeAdapterNotFoundException if no TypeAdapter was found. This is especially
     * the case for primitives like byte, short and float.
     */
    <T> TypeAdapter<T> getAdapter(Class<T> type, Class<?>... generics) throws TypeAdapterNotFoundException;
}
