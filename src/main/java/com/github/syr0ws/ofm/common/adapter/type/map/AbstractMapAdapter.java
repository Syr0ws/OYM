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

package com.github.syr0ws.ofm.common.adapter.type.map;

import com.github.syr0ws.ofm.api.adapter.TypeAdaptationException;
import com.github.syr0ws.ofm.api.adapter.TypeAdapter;
import com.github.syr0ws.ofm.api.node.Node;
import com.github.syr0ws.ofm.api.node.ObjectNode;
import com.github.syr0ws.ofm.common.util.NodeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMapAdapter<M extends Map<String, V>, V> implements TypeAdapter<M> {

    private final TypeAdapter<V> adapter;

    public AbstractMapAdapter(@NotNull TypeAdapter<V> adapter) {
        this.adapter = adapter;
    }

    public abstract M getMapInstance();

    @Override
    public M read(Node node) throws TypeAdaptationException {

        ObjectNode objectNode = NodeUtil.cast(node, ObjectNode.class);
        Map<String, Node> nodes = objectNode.getProperties();

        M map = this.getMapInstance();

        for(Map.Entry<String, Node> entry : nodes.entrySet()) {
            V value = this.adapter.read(entry.getValue());
            map.put(entry.getKey(), value);
        }

        return map;
    }

    @Override
    public Node write(M value) throws TypeAdaptationException {

        ObjectNode object = new ObjectNode(new HashMap<>());

        for(Map.Entry<String, V> entry : value.entrySet()) {

            Node node = this.adapter.write(entry.getValue());

            object.addProperty(entry.getKey(), node);
        }

        return object;
    }
}
