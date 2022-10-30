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

package com.github.syr0ws.ofm.common.adapter.type;

import com.github.syr0ws.ofm.api.adapter.TypeAdaptationException;
import com.github.syr0ws.ofm.api.adapter.TypeAdapter;
import com.github.syr0ws.ofm.api.node.Node;
import com.github.syr0ws.ofm.api.node.ScalarNode;
import com.github.syr0ws.ofm.common.util.NodeUtil;

public class BooleanAdapter implements TypeAdapter<Boolean> {

    @Override
    public Boolean read(Node node) throws TypeAdaptationException {

        ScalarNode element = NodeUtil.cast(node, ScalarNode.class);

        if(!element.isBoolean())
            throw new TypeAdaptationException("Node is not a boolean.");

        return element.asBoolean();
    }

    @Override
    public Node write(Boolean value) throws TypeAdaptationException {
        return new ScalarNode(value);
    }
}