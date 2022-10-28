package com.github.syr0ws.oym.api;

import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProviderModel;
import com.github.syr0ws.oym.api.instance.InstanceProviderModel;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.api.section.ConfigurationSection;

import java.io.InputStream;

public interface ObjectFileMapper {

    ConfigurationSection readSection(InputStream stream) throws NodeParsingException;

    String writeSection(ConfigurationSection section);

    ObjectNode readNode(InputStream stream) throws NodeParsingException;

    String writeNode(ObjectNode node);

    TypeAdapterFactory getTypeAdapterFactory();

    TypeAdapterProviderModel getTypeAdapterProviderModel();

    InstanceProviderModel getInstanceProviderModel();
}
