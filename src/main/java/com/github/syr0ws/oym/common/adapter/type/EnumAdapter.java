package com.github.syr0ws.oym.common.adapter.type;

import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.common.util.NodeUtil;
import org.jetbrains.annotations.NotNull;

public class EnumAdapter<T extends Enum<T>> implements TypeAdapter<T> {

    private final Class<T> enumType;

    public EnumAdapter(@NotNull Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T read(Node node) throws TypeAdaptationException {

        ScalarNode element = NodeUtil.cast(node, ScalarNode.class);

        if(!element.isString())
            throw new TypeAdaptationException("YamlElement is not a String.");

        String value = element.asString();

        try {
            return Enum.valueOf(this.enumType, value);
        } catch (IllegalArgumentException exception) {
            throw new TypeAdaptationException(String.format("Value '%s' is not part of enum %s'.", value, this.enumType.getName()));
        }
    }

    @Override
    public Node write(T value) {
        return new ScalarNode(value.name());
    }
}
