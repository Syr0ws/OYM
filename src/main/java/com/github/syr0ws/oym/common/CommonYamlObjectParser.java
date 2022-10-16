package com.github.syr0ws.oym.common;

import com.github.syr0ws.oym.api.YamlObjectParser;
import com.github.syr0ws.oym.api.YamlObjectParsingException;
import com.github.syr0ws.oym.api.node.CollectionNode;
import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.common.util.TypeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommonYamlObjectParser implements YamlObjectParser {

    @Override
    public ObjectNode parse(Map<String, Object> data) throws YamlObjectParsingException {

        Map<String, Node> nodes = new HashMap<>();

        for(Map.Entry<String, Object> entry : data.entrySet()) {

            String key = entry.getKey();
            Object object = entry.getValue();

            Node node = this.getNode(object);

            nodes.put(key, node);
        }

        return new ObjectNode(nodes);
    }

    private Node getNode(Object object) throws YamlObjectParsingException {

        Class<?> type = object.getClass();

        if(TypeUtil.isPrimitive(type)) {
            return new ScalarNode(object);
        }

        if(TypeUtil.isCollection(object)) {
            return this.getCollection(object);
        }

        if(TypeUtil.isMap(object)) {
            return this.getObject(object);
        }

        throw new YamlObjectParsingException("Unsupported object type: " + object.getClass().getName());
    }

    private CollectionNode getCollection(Object object) throws YamlObjectParsingException {

        @SuppressWarnings("unchecked")
        Collection<Object> collection = (Collection<Object>) object;

        Collection<Node> nodes = new ArrayList<>(collection.size());

        for(Object internal : collection) {

            Node node = this.getNode(internal);

            nodes.add(node);
        }

        return new CollectionNode(nodes);
    }

    private ObjectNode getObject(Object object) throws YamlObjectParsingException {

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) object;

        return this.parse(map);
    }
}
