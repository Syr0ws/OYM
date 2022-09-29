package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.node.YamlElement;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.common.util.NodeUtil;

public class StringAdapter implements TypeAdapter<String> {

    @Override
    public String read(YamlNode node) throws TypeAdaptationException {

        YamlElement element = NodeUtil.cast(node, YamlElement.class);

        if(!element.isString())
            throw new TypeAdaptationException("YamlElement is not String.");

        return element.asString();
    }

    @Override
    public YamlNode write(String value) {
        return new YamlElement(value);
    }
}
