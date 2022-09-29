package com.github.syr0ws.oym.api;

import com.github.syr0ws.oym.api.node.YamlObject;

import java.util.Map;

public interface YamlObjectParser {

    YamlObject parse(Map<String, Object> data) throws YamlObjectParsingException;
}
