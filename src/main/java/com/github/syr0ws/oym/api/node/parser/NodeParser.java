package com.github.syr0ws.oym.api.node.parser;

import com.github.syr0ws.oym.api.node.ObjectNode;

import java.util.Map;

public interface NodeParser {

    ObjectNode parse(Map<String, Object> data) throws NodeParsingException;
}
