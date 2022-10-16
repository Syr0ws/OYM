package com.github.syr0ws.oym.api;

import com.github.syr0ws.oym.api.node.ObjectNode;

import java.util.Map;

public interface YamlObjectParser {

    ObjectNode parse(Map<String, Object> data) throws YamlObjectParsingException;
}
