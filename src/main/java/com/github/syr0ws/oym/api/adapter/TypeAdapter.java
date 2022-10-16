package com.github.syr0ws.oym.api.adapter;

import com.github.syr0ws.oym.api.node.Node;

public interface TypeAdapter<T> {

    T read(Node node) throws TypeAdaptationException;

    Node write(T value) throws TypeAdaptationException;
}
