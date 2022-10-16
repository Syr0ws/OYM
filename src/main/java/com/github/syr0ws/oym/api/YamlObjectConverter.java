package com.github.syr0ws.oym.api;

import com.github.syr0ws.oym.api.node.ObjectNode;

import java.util.Map;

public interface YamlObjectConverter {

    Map<String, Object> convert(ObjectNode object);
}