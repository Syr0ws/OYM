package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.common.util.NodeUtil;

public class IntegerAdapter implements TypeAdapter<Integer> {

    @Override
    public Integer read(Node node) throws TypeAdaptationException {

        ScalarNode element = NodeUtil.cast(node, ScalarNode.class);

        if(!element.isInt())
            throw new TypeAdaptationException("Node is not an integer.");

        return element.asInt();
    }

    @Override
    public Node write(Integer value) {
        return new ScalarNode(value);
    }
}
