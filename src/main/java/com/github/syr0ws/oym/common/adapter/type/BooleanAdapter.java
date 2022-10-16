package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.common.util.NodeUtil;

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
