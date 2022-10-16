package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.common.util.NodeUtil;

public class DoubleAdapter implements TypeAdapter<Double> {

    @Override
    public Double read(Node node) throws TypeAdaptationException {

        ScalarNode element = NodeUtil.cast(node, ScalarNode.class);

        if(!element.isDouble())
            throw new TypeAdaptationException("Node is not a double.");

        return element.asDouble();
    }

    @Override
    public Node write(Double value) {
        return new ScalarNode(value);
    }
}
