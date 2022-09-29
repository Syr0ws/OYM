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
import com.github.syr0ws.oym.common.util.ReflectionUtil;
import com.github.syr0ws.oym.common.util.TypeUtil;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

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
    public YamlNode write(T value) throws TypeAdaptationException {

        YamlObject object = new YamlObject();

        StructureSchema<T> schema = this.builder.build(this.type);

        for(StructureField<?> field : schema.getFields()) {

            YamlNode node = this.mapValue(field, value);
            node.setComments(Arrays.asList(field.getComments())); // Adding comments to the node.

            object.addProperty(field.getKey(), node);
        }

        return object;
    }

    @SuppressWarnings("unchecked")
    private <S> void bindValue(StructureField<S> field, T instance, YamlObject object) throws TypeAdaptationException {

        Class<S> type = field.getType();
        S value;

        Class<?>[] generics;

        try { generics = GenericUtil.getGenericTypesArray(field.getField());
        } catch (Exception exception) { throw new TypeAdaptationException(String.format("Cannot retrieve generic types of type '%s'.", type.getName()), exception); }

        // Prise en compte génériques.
        TypeAdapter<S> adapter = this.factory.getAdapter(type, generics);

        YamlNode internalNode = object.getProperty(field.getKey());

        value = adapter.read(internalNode);

        try { ReflectionUtil.setValue(field, instance, value);
        } catch (Exception exception) { throw new TypeAdaptationException("Cannot bind field value.", exception); }
    }

    private <S> YamlNode mapValue(StructureField<S> field, T instance) throws TypeAdaptationException {

        Class<S> type = field.getType();

        Class<?>[] generics;

        try { generics = GenericUtil.getGenericTypesArray(field.getField());
        } catch (Exception exception) { throw new TypeAdaptationException(String.format("Cannot retrieve generic types of type '%s'.", type.getName()), exception); }

        // Prise en compte génériques.
        TypeAdapter<S> adapter = this.factory.getAdapter(type, generics);

        S value;

        try { value = ReflectionUtil.getValue(field, instance);
        } catch (Exception exception) { throw new TypeAdaptationException("Cannot retrieve field value.", exception); }

        return adapter.write(value);
    }
}
