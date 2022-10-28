package com.github.syr0ws.oym.common;

import com.github.syr0ws.oym.api.ObjectFileMapper;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProviderModel;
import com.github.syr0ws.oym.api.instance.InstanceProviderModel;
import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.api.schema.StructureSchemaBuilder;
import com.github.syr0ws.oym.api.section.ConfigurationSection;
import com.github.syr0ws.oym.common.adapter.TypeAdapterFactoryProvider;
import com.github.syr0ws.oym.common.adapter.provider.CommonTypeAdapterProviderModel;
import com.github.syr0ws.oym.common.adapter.provider.type.*;
import com.github.syr0ws.oym.common.adapter.provider.type.collection.ArrayListAdapterProvider;
import com.github.syr0ws.oym.common.adapter.provider.type.collection.ListAdapterProvider;
import com.github.syr0ws.oym.common.adapter.provider.type.collection.SetAdapterProvider;
import com.github.syr0ws.oym.common.adapter.provider.type.map.HashMapAdapterProvider;
import com.github.syr0ws.oym.common.adapter.provider.type.map.MapAdapterProvider;
import com.github.syr0ws.oym.common.instance.CommonInstanceProviderModel;
import com.github.syr0ws.oym.common.instance.CommonInstanceProviderService;
import com.github.syr0ws.oym.common.schema.CommonStructureSchemaBuilder;
import com.github.syr0ws.oym.common.section.NodeSection;

import java.io.InputStream;

public abstract class AbstractObjectFileMapper implements ObjectFileMapper {

    private final InstanceProviderModel instanceProviderModel;

    private final TypeAdapterProviderModel typeAdapterProviderModel;
    private final TypeAdapterFactory typeAdapterFactory;

    public AbstractObjectFileMapper() {

        StructureSchemaBuilder schemaBuilder = new CommonStructureSchemaBuilder();

        this.instanceProviderModel = new CommonInstanceProviderModel();
        InstanceProviderService instanceProviderService = new CommonInstanceProviderService(this.instanceProviderModel);

        this.typeAdapterProviderModel = new CommonTypeAdapterProviderModel();
        this.typeAdapterFactory = new TypeAdapterFactoryProvider(this.typeAdapterProviderModel, instanceProviderService, schemaBuilder);

        this.initAdapters();
    }

    protected void initAdapters() {
        this.typeAdapterProviderModel.addProvider(new StringAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new IntegerAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new DoubleAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new LongAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new UUIDAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new EnumTypeAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new ListAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new ArrayListAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new SetAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new HashMapAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new MapAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new HashMapAdapterProvider<>());
    }

    @Override
    public ConfigurationSection readSection(InputStream stream) throws NodeParsingException {
        ObjectNode node = this.readNode(stream);
        return new NodeSection(this.typeAdapterFactory, node);
    }

    @Override
    public String writeSection(ConfigurationSection section) {
        ObjectNode node = section.getNode();
        return this.writeNode(node);
    }

    @Override
    public TypeAdapterFactory getTypeAdapterFactory() {
        return this.typeAdapterFactory;
    }

    @Override
    public TypeAdapterProviderModel getTypeAdapterProviderModel() {
        return this.typeAdapterProviderModel;
    }

    @Override
    public InstanceProviderModel getInstanceProviderModel() {
        return this.instanceProviderModel;
    }
}
