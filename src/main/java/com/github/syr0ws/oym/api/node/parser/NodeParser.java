package com.github.syr0ws.oym.api.node.parser;

import com.github.syr0ws.oym.api.node.ObjectNode;

import java.util.Map;

public interface NodeParser {

    /**
     * Parses a map of objects into an ObjectNode that can be manipulated by the system.
     * @param data the data to be parsed.
     * @return an ObjectNode that represents the map passed as parameter.
     * @throws NodeParsingException When no Node can be found for an object type. This is especially the
     * case when the Map contains an Object which neither a primitive wrapper nor a collection nor a
     * Map<String, Object>.
     */
    ObjectNode parse(Map<String, Object> data) throws NodeParsingException;
}
