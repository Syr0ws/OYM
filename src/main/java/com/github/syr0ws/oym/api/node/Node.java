package com.github.syr0ws.oym.api.node;

import java.util.List;

/**
 * Represents an data.
 */
public interface Node {

    /**
     * Check if the current instance is an ObjectNode.
     * @return true if it is an ObjectNode or else false.
     */
    boolean isObject();

    /**
     * Check if the current instance is a CollectionNode.
     * @return true if it is an CollectionNode or else false.
     */
    boolean isCollection();

    /**
     * Get the Object represented by the node.
     * @return an object.
     */
    Object get();

    /**
     * Set comments for the node.
     * @param comments a list of comments that can be empty.
     */
    void setComments(List<String> comments);

    /**
     * Get the comments associated with the node.
     * @return a list of comments that can be empty if no comment was set.
     */
    List<String> getComments();
}
