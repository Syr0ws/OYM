package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.node.YamlElement;
import com.github.syr0ws.oym.api.node.YamlObject;
import com.github.syr0ws.oym.api.schema.StructureField;
import com.github.syr0ws.oym.api.schema.StructureSchema;
import com.github.syr0ws.oym.api.schema.StructureSchemaBuilder;
import com.github.syr0ws.oym.common.util.GenericUtil;
import com.github.syr0ws.oym.common.util.NodeUtil;
import com.github.syr0ws.oym.common.util.TypeUtil;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

public class ObjectAdapter<T> implements TypeAdapter<T> {

    private final StructureSchemaBuilder builder;
    private final InstanceProviderService service;
    private final TypeAdapterFactory factory;
    private final Class<T> type;

    public ObjectAdapter(@NotNull StructureSchemaBuilder builder, @NotNull InstanceProviderService service, @NotNull TypeAdapterFactory factory, @NotNull Class<T> type) {
        this.builder = builder;
        this.service = service;
        this.factory = factory;
        this.type = type;
    }

    @Override
    public T read(YamlNode node) throws TypeAdaptationException {

        YamlObject object = NodeUtil.cast(node, YamlObject.class);

        T instance;

        try { instance = this.service.provide(this.type, node);
        } catch (InstantiationException exception) { throw new TypeAdaptationException("Cannot adapt object.", exception); }

        StructureSchema<T> schema = this.builder.build(this.type);

        for(StructureField<?> field : schema.getFields()) {
            this.bindValue(field, instance, object);
        }

        return instance;
    }

    @Override
    public YamlNode write(T value) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private <S> void bindValue(StructureField<S> field, T instance, YamlObject object) throws TypeAdaptationException {

        Class<S> type = field.getType();
        S value;

        if(TypeUtil.isPrimitive(type)) {

            YamlElement element = (YamlElement) object.getProperty(field.getKey());
            value = (S) element.get();

        } else {

            Class<?>[] generics;

            try { generics = GenericUtil.getGenericTypesArray(field.getField());
            } catch (Exception exception) { throw new TypeAdaptationException(String.format("Cannot retrieve generic types of type '%s'.", type.getName()), exception); }

            // Prise en compte génériques.
            TypeAdapter<S> adapter = this.factory.getAdapter(type, generics);

            YamlNode internalNode = object.getProperty(field.getKey());

            value = adapter.read(internalNode);
        }

        this.setValue(field, instance, value);

    }

    private <V> void setValue(StructureField<V> structureField, T instance, V value) throws TypeAdaptationException {

        Optional<Method> optional = structureField.getSetter();

        if(optional.isPresent()) {

            // Using the setter.
            Method setter = optional.get();
            boolean accessible = setter.isAccessible();

            try {
                setter.setAccessible(true);
                setter.invoke(instance, value);
            } catch (Exception exception) {
                throw new TypeAdaptationException("Cannot use setter to bind a value.");
            } finally {
                setter.setAccessible(accessible);
            }

        } else {

            // Using the field.
            Field field = structureField.getField();
            boolean accessible = field.isAccessible();

            try {
                field.setAccessible(true);
                field.set(instance, value);
            } catch (Exception exception) {
                throw new TypeAdaptationException("Cannot bind a value to a field.", exception);
            } finally {
                field.setAccessible(accessible);
            }
        }
    }
}
