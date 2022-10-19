package com.github.syr0ws.oym.common.node.parser;

import com.github.syr0ws.oym.api.node.parser.NodeParser;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.api.node.CollectionNode;
import com.github.syr0ws.oym.api.node.ScalarNode;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.common.util.TypeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommonNodeParser implements NodeParser {

    @Override
    public ObjectNode parse(Map<String, Object> data) throws NodeParsingException {

        Map<String, Node> nodes = new HashMap<>();

        for(Map.Entry<String, Object> entry : data.entrySet()) {

            String key = entry.getKey();
            Object object = entry.getValue();

            Node node = this.getNode(object);

            nodes.put(key, node);
        }

        return new ObjectNode(nodes);
    }

    private Node getNode(Object object) throws NodeParsingException {

        Class<?> type = object.getClass();

        if(TypeUtil.isPrimitiveWrapper(type)) {
            return new ScalarNode(object);
        }

        if(TypeUtil.isCollection(object)) {
            return this.getCollection(object);
        }

        if(TypeUtil.isMap(object)) {
            return this.getObject(object);
        }

        throw new NodeParsingException("Unsupported object type: " + object.getClass().getName());
    }

    private CollectionNode getCollection(Object object) throws NodeParsingException {

        @SuppressWarnings("unchecked")
        Collection<Object> collection = (Collection<Object>) object;

        Collection<Node> nodes = new ArrayList<>(collection.size());

        for(Object internal : collection) {

            Node node = this.getNode(internal);

            nodes.add(node);
        }

        return new CollectionNode(nodes);
    }

    private ObjectNode getObject(Object object) throws NodeParsingException {

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) object;

        return this.parse(map);
    }
}
