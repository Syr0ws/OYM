package com.github.syr0ws.oym.api.mapper;

import com.github.syr0ws.oym.api.node.ObjectNode;

/**
 * Map an ObjectNode to another object that depends on a data representation language.
 */
public interface Mapper<T> {

    /**
     * Execute the mapping.
     * @param node a node to map.
     * @return an object that represents the node in a specific data representation language.
     */
    T map(ObjectNode node);
}
