package com.github.syr0ws.oym.api.adapter.provider;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;

public interface TypeAdapterProvider<T> {

    /**
     * Instantiate a TypeAdapter which is able to manipulate objects of type passed as parameter.
     * @param type the type to be manipulated by the TypeAdapter instance.
     * @param factory a TypeAdapterFactory instance. It is especially used to retrieve adapters for generic types.
     * @param generics If the type to manipulate has generic parameters, they must be specified in this field.
     * @return the corresponding TypeAdapter instance.
     */
    TypeAdapter<T> provide(Class<T> type, TypeAdapterFactory factory, Class<?>... generics);

    /**
     * Get the object type manipulated by the provider.
     * @return an object type.
     */
    Class<? super T> getType();
}
