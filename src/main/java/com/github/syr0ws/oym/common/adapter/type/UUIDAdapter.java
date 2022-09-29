package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.node.YamlElement;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.common.util.NodeUtil;

import java.util.UUID;

public class UUIDAdapter implements TypeAdapter<UUID> {

    @Override
    public UUID read(YamlNode node) throws TypeAdaptationException {

        YamlElement element = NodeUtil.cast(node, YamlElement.class);

        if(!element.isString())
            throw new TypeAdaptationException("YamlElement is not a String.");

        return UUID.fromString(element.asString());
    }

    @Override
    public YamlNode write(UUID value) {
        return new YamlElement(value.toString());
    }
}
