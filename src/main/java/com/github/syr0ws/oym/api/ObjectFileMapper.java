package com.github.syr0ws.oym.api;

import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProviderModel;
import com.github.syr0ws.oym.api.instance.InstanceProviderModel;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.api.section.ConfigurationSection;

import java.io.InputStream;

public interface ObjectFileMapper {

    /**
     * Convert an InputStream to a ConfigurationSection depending on a data representation language.
     * @param stream InputStream to convert.
     * @return ConfigurationSection that represents to the InputStream.
     * @throws NodeParsingException If an error occurs during the conversion.
     */
    ConfigurationSection readSection(InputStream stream) throws NodeParsingException;

    /**
     * Convert a ConfigurationSection to a String depending on a data representation language.
     * @param section ConfigurationSection to convert.
     * @return String that is the representation of the ConfigurationSection in a specific data
     * representation language.
     */
    String writeSection(ConfigurationSection section);

    /**
     * Convert an ObjectNode to a ConfigurationSection depending on a data representation language.
     * @param stream ObjectNode to convert.
     * @return ObjectNode that represents to the InputStream.
     * @throws NodeParsingException If an error occurs during the conversion.
     */
    ObjectNode readNode(InputStream stream) throws NodeParsingException;

    /**
     * Convert an ObjectNode to a String depending on a data representation language.
     * @param node ObjectNode to convert.
     * @return String that is the representation of the ObjectNode in a specific data
     * representation language.
     */
    String writeNode(ObjectNode node);

    /**
     * @return TypeAdapterFactory instance used to retrieve TypeAdapter objects.
     */
    TypeAdapterFactory getTypeAdapterFactory();

    /**
     * @return TypeAdapterProviderModel instance which can be used to register new TypeAdapter providers.
     */
    TypeAdapterProviderModel getTypeAdapterProviderModel();

    /**
     * @return InstanceProviderModel instance which can be used to register new instance providers.
     */
    InstanceProviderModel getInstanceProviderModel();
}
