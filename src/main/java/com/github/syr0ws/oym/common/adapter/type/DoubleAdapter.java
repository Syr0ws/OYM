package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.YamlElement;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.common.util.NodeUtil;

public class DoubleAdapter implements TypeAdapter<Double> {

    @Override
    public Double read(YamlNode node) throws TypeAdaptationException {

        YamlElement element = NodeUtil.cast(node, YamlElement.class);

        if(!element.isDouble())
            throw new TypeAdaptationException("Node is not a double.");

        return element.asDouble();
    }

    @Override
    public YamlNode write(Double value) {
        return new YamlElement(value);
    }
}
