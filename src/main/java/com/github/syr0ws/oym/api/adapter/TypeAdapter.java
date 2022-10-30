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

package com.github.syr0ws.oym.api.adapter;

import com.github.syr0ws.oym.api.node.Node;

/**
 * This interface adapts an object of type T from and to nodes.
 */
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
