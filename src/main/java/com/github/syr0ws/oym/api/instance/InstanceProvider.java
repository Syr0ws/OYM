package com.github.syr0ws.oym.api.instance;

import com.github.syr0ws.oym.api.node.Node;

@FunctionalInterface
public interface InstanceProvider<T> {

    T provide(Node node);
}
