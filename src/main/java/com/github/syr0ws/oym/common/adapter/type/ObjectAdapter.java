package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.TypeAdapterNotFoundException;
import com.github.syr0ws.oym.api.instance.InstanceException;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.schema.StructureField;
import com.github.syr0ws.oym.api.schema.StructureSchema;
import com.github.syr0ws.oym.api.schema.StructureSchemaBuilder;
import com.github.syr0ws.oym.common.util.GenericUtil;
import com.github.syr0ws.oym.common.util.NodeUtil;
import com.github.syr0ws.oym.common.util.ReflectionUtil;
import com.github.syr0ws.oym.api.node.Node;
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
    public T read(Node node) throws TypeAdaptationException {

        ObjectNode object = NodeUtil.cast(node, ObjectNode.class);

        T instance;

        try { instance = this.service.getInstance(this.type, node);
        } catch (InstanceException exception) { throw new TypeAdaptationException("Cannot adapt object.", exception); }

        StructureSchema<?> schema = this.builder.build(instance.getClass());

        for(StructureField<?> field : schema.getFields()) {
            this.bindValue(field, instance, object);
        }

        return instance;
    }

    @Override
    public Node write(T value) throws TypeAdaptationException {

        ObjectNode object = new ObjectNode();

        StructureSchema<?> schema = this.builder.build(value.getClass());

        for(StructureField<?> field : schema.getFields()) {

            Node node = this.mapValue(field, value);
            node.setComments(Arrays.asList(field.getComments())); // Adding comments to the node.

            object.addProperty(field.getKey(), node);
        }

        return object;
    }

    private <S> void bindValue(StructureField<S> field, T instance, ObjectNode object) throws TypeAdaptationException {

        Class<S> type = field.getType();
        S value;

        Class<?>[] generics;

        try { generics = GenericUtil.getGenericTypesArray(field.getField());
        } catch (Exception exception) { throw new TypeAdaptationException(String.format("Cannot retrieve generic types of type '%s'.", type.getName()), exception); }

        // Prise en compte génériques.
        TypeAdapter<S> adapter;

        try { adapter = this.factory.getAdapter(type, generics);
        } catch (TypeAdapterNotFoundException exception) { throw new TypeAdaptationException(exception); }

        Node internalNode = object.getProperty(field.getKey());

        value = adapter.read(internalNode);

        try { ReflectionUtil.setValue(field, instance, value);
        } catch (Exception exception) { throw new TypeAdaptationException("Cannot bind field value.", exception); }
    }

    private <S> Node mapValue(StructureField<S> field, T instance) throws TypeAdaptationException {

        Class<S> type = field.getType();

        Class<?>[] generics;

        try { generics = GenericUtil.getGenericTypesArray(field.getField());
        } catch (Exception exception) { throw new TypeAdaptationException(String.format("Cannot retrieve generic types of type '%s'.", type.getName()), exception); }

        // Prise en compte génériques.
        TypeAdapter<S> adapter;

        try { adapter = this.factory.getAdapter(type, generics);
        } catch (TypeAdapterNotFoundException exception) { throw new RuntimeException(exception); }

        S value;

        try { value = ReflectionUtil.getValue(field, instance);
        } catch (Exception exception) { throw new TypeAdaptationException("Cannot retrieve field value.", exception); }

        return adapter.write(value);
    }
}
