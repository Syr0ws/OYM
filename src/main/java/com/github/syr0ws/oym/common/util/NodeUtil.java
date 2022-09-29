package com.github.syr0ws.oym.common.util;

import com.github.syr0ws.oym.api.node.YamlNodeException;
import com.github.syr0ws.oym.api.node.YamlNode;

public class NodeUtil {

    public static <T extends YamlNode> T cast(YamlNode node, Class<T> type) throws YamlNodeException {

        if(!type.isInstance(node))
            throw new YamlNodeException("Invalid node type.");

        return type.cast(node);
    }
}
