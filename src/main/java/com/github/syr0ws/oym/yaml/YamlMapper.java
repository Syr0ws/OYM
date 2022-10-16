package com.github.syr0ws.oym.yaml;

import com.github.syr0ws.oym.api.mapper.Mapper;
import com.github.syr0ws.oym.api.node.CollectionNode;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.api.node.ObjectNode;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.comments.CommentLine;
import org.yaml.snakeyaml.comments.CommentType;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YamlMapper implements Mapper<MappingNode> {

    private static final Representer DEFAULT_REPRESENTER = new Representer();

    static {
        DEFAULT_REPRESENTER.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        DEFAULT_REPRESENTER.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
    }

    @Override
    public MappingNode map(ObjectNode objectNode) {

        List<NodeTuple> nodeTuples = new ArrayList<>();

        // Mapping each property contained in the ObjectNode.
        for(Map.Entry<String, Node> entry : objectNode.getProperties().entrySet()) {

            String key = entry.getKey();
            Node node = entry.getValue();

            org.yaml.snakeyaml.nodes.Node yamlNodeKey = DEFAULT_REPRESENTER.represent(key);
            org.yaml.snakeyaml.nodes.Node yamlNode;

            // A node can be: a scalar, a collection, an object.
            // As a scalar is not well-defined yet, it must be in the else case.
            if(node.isObject()) {

                // Object
                yamlNode = this.map((ObjectNode) node);

            } else if(node.isCollection()) {

                // Collection
                CollectionNode collection = (CollectionNode) node;

                List<Object> list = new ArrayList<>();
                collection.forEach(contained -> list.add(contained.get()));

                yamlNode = DEFAULT_REPRESENTER.represent(list);

            } else {

                // Scalar or anything else (anything should not happen).
                yamlNode = DEFAULT_REPRESENTER.represent(node.get());
            }

            // Mapping comments.
            List<CommentLine> list = node.getComments().stream()
                    // startMark and endMark can be null as there only used for error generation.
                    .map(comment -> new CommentLine(null, null, comment, CommentType.BLOCK))
                    .collect(Collectors.toList());

            // Adding comments to the key.
            yamlNodeKey.setBlockComments(list);

            // Storing the node.
            nodeTuples.add(new NodeTuple(yamlNodeKey, yamlNode));
        }

        return new MappingNode(Tag.MAP, nodeTuples, DumperOptions.FlowStyle.BLOCK);
    }
}
