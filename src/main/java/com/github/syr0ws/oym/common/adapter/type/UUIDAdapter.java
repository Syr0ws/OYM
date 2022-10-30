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

package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.common.util.NodeUtil;

import java.util.UUID;

public class UUIDAdapter implements TypeAdapter<UUID> {

    @Override
    public UUID read(Node node) throws TypeAdaptationException {

        ScalarNode element = NodeUtil.cast(node, ScalarNode.class);

        if(!element.isString())
            throw new TypeAdaptationException("YamlElement is not a String.");

        return UUID.fromString(element.asString());
    }

    @Override
    public Node write(UUID value) {
        return new ScalarNode(value.toString());
    }
}
