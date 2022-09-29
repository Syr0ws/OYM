package com.github.syr0ws.oym.api.adapter;

import com.github.syr0ws.oym.api.node.YamlNode;

public interface TypeAdapter<T> {

    T read(YamlNode node) throws TypeAdaptationException;

    YamlNode write(T value);
}
