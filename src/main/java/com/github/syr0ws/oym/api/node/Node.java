/*
 *    Copyright 2022 syr0ws
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
