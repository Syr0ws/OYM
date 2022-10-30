package com.github.syr0ws.oym.api.instance;

import com.github.syr0ws.oym.api.node.Node;

/**
 * Interface used to concretely provide an instance depending on a type.
 */
@FunctionalInterface
public interface InstanceProvider<T> {

    /**
     * Provide an instance depending on a context node.
     * @param node a context node.
     * @return an instance of T.
     */
    T provide(Node node);
}
