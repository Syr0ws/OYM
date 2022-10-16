package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.common.util.NodeUtil;

public class LongAdapter implements TypeAdapter<Long> {

    @Override
    public Long read(Node node) throws TypeAdaptationException {

        ScalarNode element = NodeUtil.cast(node, ScalarNode.class);

        if(!element.isLong())
            throw new TypeAdaptationException("Node is not a long.");

        return element.asLong();
    }

    @Override
    public Node write(Long value) {
        return new ScalarNode(value);
    }
}
