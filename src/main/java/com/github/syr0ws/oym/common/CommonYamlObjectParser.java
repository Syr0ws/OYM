package com.github.syr0ws.oym.common;

import com.github.syr0ws.oym.api.YamlObjectParser;
import com.github.syr0ws.oym.api.YamlObjectParsingException;
import com.github.syr0ws.oym.api.node.YamlCollection;
import com.github.syr0ws.oym.api.node.YamlElement;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.api.node.YamlObject;
import com.github.syr0ws.oym.common.util.TypeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommonYamlObjectParser implements YamlObjectParser {

    @Override
    public YamlObject parse(Map<String, Object> data) throws YamlObjectParsingException {

        Map<String, YamlNode> nodes = new HashMap<>();

        for(Map.Entry<String, Object> entry : data.entrySet()) {

            String key = entry.getKey();
            Object object = entry.getValue();

            YamlNode node = this.getNode(object);

            nodes.put(key, node);
        }

        return new YamlObject(nodes);
    }

    private YamlNode getNode(Object object) throws YamlObjectParsingException {

        Class<?> type = object.getClass();

        if(TypeUtil.isPrimitive(type)) {
            return new YamlElement(object);
        }

        if(TypeUtil.isCollection(object)) {
            return this.getCollection(object);
        }

        if(TypeUtil.isMap(object)) {
            return this.getObject(object);
        }

        throw new YamlObjectParsingException("Unsupported object type: " + object.getClass().getName());
    }

    private YamlCollection getCollection(Object object) throws YamlObjectParsingException {

        @SuppressWarnings("unchecked")
        Collection<Object> collection = (Collection<Object>) object;

        Collection<YamlNode> nodes = new ArrayList<>(collection.size());

        for(Object internal : collection) {

            YamlNode node = this.getNode(internal);

            nodes.add(node);
        }

        return new YamlCollection(nodes);
    }

    private YamlObject getObject(Object object) throws YamlObjectParsingException {

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) object;

        return this.parse(map);
    }
}
