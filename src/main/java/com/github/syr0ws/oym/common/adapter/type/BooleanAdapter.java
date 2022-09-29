package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.YamlElement;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.common.util.NodeUtil;

public class BooleanAdapter implements TypeAdapter<Boolean> {

    @Override
    public Boolean read(YamlNode node) throws TypeAdaptationException {

        YamlElement element = NodeUtil.cast(node, YamlElement.class);

        if(!element.isBoolean())
            throw new TypeAdaptationException("Node is not a boolean.");

        return element.asBoolean();
    }

    @Override
    public YamlNode write(Boolean value) throws TypeAdaptationException {
        return new YamlElement(value);
    }
}
