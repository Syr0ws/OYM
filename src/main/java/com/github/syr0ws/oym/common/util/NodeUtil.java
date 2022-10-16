package com.github.syr0ws.oym.common.util;

import com.github.syr0ws.oym.api.node.YamlNodeException;
import com.github.syr0ws.oym.api.node.Node;

public class NodeUtil {

    public static <T extends Node> T cast(Node node, Class<T> type) throws YamlNodeException {

        if(!type.isInstance(node))
            throw new YamlNodeException(String.format("Cannot cast %s to %s.", node.getClass().getName(), type.getName()));

        return type.cast(node);
    }
}
