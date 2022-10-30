package com.github.syr0ws.oym.api.instance;

import com.github.syr0ws.oym.api.node.Node;

/**
 * Provides instances depending on types during the mapping process. It should be
 * able to use an InstanceProvider or to create it using reflection.
 */
public interface InstanceProviderService {

    /**
     * Provide an instance of Class<T> depending on a context node. The context node
     * represents the node which will be converted to an object of type T during the
     * mapping process.
     * @param type a type of object to get the instance of.
     * @param node a context node.
     * @return an object of type T.
     * @throws InstanceException if an error occurs during the operation, especially when the
     * instance is created manually using reflection.
     */
    <T> T getInstance(Class<T> type, Node node) throws InstanceException;
}
