package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.node.YamlElement;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.common.util.NodeUtil;

public class IntegerAdapter implements TypeAdapter<Integer> {

    @Override
    public Integer read(YamlNode node) throws TypeAdaptationException {

        YamlElement element = NodeUtil.cast(node, YamlElement.class);

        if(!(element.get() instanceof Number))
            throw new TypeAdaptationException("Node is not a number.");

        return element.asInt();
    }

    @Override
    public YamlNode write(Integer value) {
        return new YamlElement(value);
    }
}
