package com.github.syr0ws.oym.common.adapter;

import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.TypeAdapterModel;
import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import com.github.syr0ws.oym.api.schema.StructureSchemaBuilder;
import com.github.syr0ws.oym.common.adapter.type.*;
import com.github.syr0ws.oym.common.adapter.type.collection.ArrayListAdapter;
import com.github.syr0ws.oym.common.adapter.type.collection.HashSetAdapter;
import com.github.syr0ws.oym.common.adapter.type.map.HashMapAdapter;
import com.github.syr0ws.oym.common.util.PrimitiveUtil;
import com.github.syr0ws.oym.common.util.TypeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TypeAdapterFactoryProvider implements TypeAdapterFactory {

    private static final TypeAdapterModel DEFAULT_TYPE_ADAPTER_MODEL = new TypeAdapterModelProvider();

    static {
        DEFAULT_TYPE_ADAPTER_MODEL.addAdapter(String.class, new StringAdapter());
        DEFAULT_TYPE_ADAPTER_MODEL.addAdapter(Boolean.class, new BooleanAdapter());
        DEFAULT_TYPE_ADAPTER_MODEL.addAdapter(Integer.class, new IntegerAdapter());
        DEFAULT_TYPE_ADAPTER_MODEL.addAdapter(Long.class, new LongAdapter());
        DEFAULT_TYPE_ADAPTER_MODEL.addAdapter(Double.class, new DoubleAdapter());
    }

    private final StructureSchemaBuilder builder;
    private final InstanceProviderService service;

    public TypeAdapterFactoryProvider(@NotNull StructureSchemaBuilder builder, @NotNull InstanceProviderService service) {
        this.builder = builder;
        this.service = service;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> getAdapter(@NotNull Class<T> type, Class<?>... generics) {

        TypeAdapter<T> adapter;

        // Retrieving and using the wrapper if the type is a primitive.
        if(type.isPrimitive())
            type = PrimitiveUtil.getWrapper(type);

        if(DEFAULT_TYPE_ADAPTER_MODEL.hasAdapter(type)) {

            adapter = DEFAULT_TYPE_ADAPTER_MODEL.getAdapter(type);

        } else if(TypeUtil.isEnum(type)) {

            adapter = (TypeAdapter<T>) this.getEnumAdapter(type);

        } else if(Collection.class.isAssignableFrom(type)) {

            if(generics.length != 1)
                throw new IllegalArgumentException("Missing collection generic type or too much generic types.");

            adapter = this.getCollectionAdapter(type, generics[0]);

        } else if(Map.class.isAssignableFrom(type)) {

            if(generics.length != 2)
                throw new IllegalArgumentException("Missing map generic types or too much generic types.");

            adapter = this.getMapAdapter(type, generics[1]);

        } else {

            adapter = new ObjectAdapter<>(this.builder, this.service, this, type);
        }

        return adapter;
    }

    @SuppressWarnings("unchecked")
    protected <T,E> TypeAdapter<T> getCollectionAdapter(Class<T> collectionType, Class<E> generic) {

        TypeAdapter<E> genericAdapter = this.getAdapter(generic);

        TypeAdapter<T> adapter = null;

        if(collectionType.equals(ArrayList.class) || collectionType.equals(List.class))
            adapter = (TypeAdapter<T>) new ArrayListAdapter<>(genericAdapter);

        if(collectionType.equals(HashSet.class) || collectionType.equals(Set.class))
            adapter = (TypeAdapter<T>) new HashSetAdapter<>(genericAdapter);

        return adapter;
    }

    @SuppressWarnings("unchecked")
    protected <M, V> TypeAdapter<M> getMapAdapter(Class<M> mapType, Class<V> generic) {

        TypeAdapter<V> genericAdapter = this.getAdapter(generic);

        TypeAdapter<M> adapter = null;

        if(mapType.equals(Map.class) || mapType.equals(HashMap.class))
            adapter = (TypeAdapter<M>) new HashMapAdapter<>(genericAdapter);

        return adapter;
    }

    @SuppressWarnings("unchecked")
    private <T extends Enum<T>> TypeAdapter<T> getEnumAdapter(Class<?> type) {
        Class<T> casted = (Class<T>) type;
        return new EnumAdapter<>(casted);
    }
}
