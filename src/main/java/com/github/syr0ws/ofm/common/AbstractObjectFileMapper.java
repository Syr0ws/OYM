/*
 *    Copyright 2022 syr0ws
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.syr0ws.ofm.common;

import com.github.syr0ws.ofm.api.ObjectFileMapper;
import com.github.syr0ws.ofm.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.ofm.api.adapter.provider.TypeAdapterProviderModel;
import com.github.syr0ws.ofm.api.instance.InstanceProviderModel;
import com.github.syr0ws.ofm.api.instance.InstanceProviderService;
import com.github.syr0ws.ofm.api.node.ObjectNode;
import com.github.syr0ws.ofm.api.node.parser.NodeParsingException;
import com.github.syr0ws.ofm.api.schema.StructureSchemaBuilder;
import com.github.syr0ws.ofm.api.section.ConfigurationSection;
import com.github.syr0ws.ofm.common.adapter.TypeAdapterFactoryProvider;
import com.github.syr0ws.ofm.common.adapter.provider.CommonTypeAdapterProviderModel;
import com.github.syr0ws.ofm.common.adapter.provider.type.*;
import com.github.syr0ws.ofm.common.adapter.provider.type.collection.ArrayListAdapterProvider;
import com.github.syr0ws.ofm.common.adapter.provider.type.collection.HashSetAdapterProvider;
import com.github.syr0ws.ofm.common.adapter.provider.type.collection.ListAdapterProvider;
import com.github.syr0ws.ofm.common.adapter.provider.type.collection.SetAdapterProvider;
import com.github.syr0ws.ofm.common.adapter.provider.type.map.HashMapAdapterProvider;
import com.github.syr0ws.ofm.common.adapter.provider.type.map.MapAdapterProvider;
import com.github.syr0ws.ofm.common.instance.CommonInstanceProviderModel;
import com.github.syr0ws.ofm.common.instance.CommonInstanceProviderService;
import com.github.syr0ws.ofm.common.schema.CommonStructureSchemaBuilder;
import com.github.syr0ws.ofm.common.section.NodeSection;

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
        this.typeAdapterProviderModel.addProvider(new BooleanAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new IntegerAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new DoubleAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new LongAdapterProvider());

        this.typeAdapterProviderModel.addProvider(new UUIDAdapterProvider());
        this.typeAdapterProviderModel.addProvider(new EnumTypeAdapterProvider<>());

        this.typeAdapterProviderModel.addProvider(new ListAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new ArrayListAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new SetAdapterProvider<>());
        this.typeAdapterProviderModel.addProvider(new HashSetAdapterProvider<>());

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
