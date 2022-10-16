package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.Node;
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
