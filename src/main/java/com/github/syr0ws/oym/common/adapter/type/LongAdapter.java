package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.YamlElement;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.common.util.NodeUtil;

public class LongAdapter implements TypeAdapter<Long> {

    @Override
    public Long read(YamlNode node) throws TypeAdaptationException {

        YamlElement element = NodeUtil.cast(node, YamlElement.class);

        if(!element.isLong())
            throw new TypeAdaptationException("Node is not a long.");

        return element.asLong();
    }

    @Override
    public YamlNode write(Long value) {
        return new YamlElement(value);
    }
}
