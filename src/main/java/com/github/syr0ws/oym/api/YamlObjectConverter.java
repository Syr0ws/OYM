package com.github.syr0ws.oym.api;

import com.github.syr0ws.oym.api.node.YamlObject;

import java.util.Map;

public interface YamlObjectConverter {

    Map<String, Object> convert(YamlObject object);
}