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

package com.github.syr0ws.ofm.api.node;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an object that is not scalar nor a collection.
 */
public class ObjectNode extends AbstractNode implements Node {

    private final Map<String, Node> nodes;

    public ObjectNode() {
        this.nodes = new LinkedHashMap<>();
    }

    public ObjectNode(Map<String, Node> nodes) {
        this();
        this.nodes.putAll(nodes);
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public Object get() {
        return this.getProperties();
    }

    public void addProperty(@NotNull String key, @NotNull Node node) {
        this.nodes.put(key, node);
    }

    public boolean removeProperty(@NotNull String key) {
        return this.nodes.remove(key) != null;
    }

    public boolean hasProperty(@NotNull String key) {
        return this.nodes.containsKey(key);
    }

    public Node getProperty(@NotNull String key) {
        return this.nodes.get(key);
    }

    public Map<String, Node> getProperties() {
        return Collections.unmodifiableMap(this.nodes);
    }
}
