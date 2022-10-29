package com.github.syr0ws.oym.api.adapter;

import com.github.syr0ws.oym.api.node.Node;

public interface TypeAdapter<T> {

    /**
     * Convert a node to an object of type T.
     * @param node the node to convert.
     * @return an object of type T corresponding to the specified node.
     * @throws TypeAdaptationException if an error occurs during the conversion.
     */
    T read(Node node) throws TypeAdaptationException;

    /**
     * Convert an object of type T to a Node.
     * @param value the instance to convert.
     * @return a node representing the specified object.
     * @throws TypeAdaptationException if an error occurs during the conversion.
     */
    Node write(T value) throws TypeAdaptationException;
}
