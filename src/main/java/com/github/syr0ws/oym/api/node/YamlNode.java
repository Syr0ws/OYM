package com.github.syr0ws.oym.api.node;

public interface YamlNode {

    boolean isObject();

    boolean isCollection();

    Object get();
}
