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

package com.github.syr0ws.ofm.common.util;

import com.github.syr0ws.ofm.api.node.Node;
import com.github.syr0ws.ofm.api.node.NodeException;

public class NodeUtil {

    public static <T extends Node> T cast(Node node, Class<T> type) throws NodeException {

        if(!type.isInstance(node))
            throw new NodeException(String.format("Cannot cast %s to %s.", node.getClass().getName(), type.getName()));

        return type.cast(node);
    }
}
