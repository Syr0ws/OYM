package com.github.syr0ws.oym.common;

import com.github.syr0ws.oym.api.YamlObjectConverter;
import com.github.syr0ws.oym.api.node.YamlCollection;
import com.github.syr0ws.oym.api.node.YamlNode;
import com.github.syr0ws.oym.api.node.YamlObject;

import java.util.*;

public class CommonYamlObjectConverter implements YamlObjectConverter {

    @Override
    public Map<String, Object> convert(YamlObject object) {

        Map<String, Object> data = new LinkedHashMap<>();

        for(Map.Entry<String, YamlNode> entry : object.getProperties().entrySet()) {

            String key = entry.getKey();
            YamlNode node = entry.getValue();

            if(node.isObject()) {

                Map<String, Object> internal = this.convert((YamlObject) node);
                data.put(key, internal);

            } else if(node.isCollection()) {

                YamlCollection collection = (YamlCollection) node;

                List<Object> list = new ArrayList<>();
                collection.forEach(contained -> list.add(contained.get()));

                data.put(key, list);

            } else data.put(key, node.get());
        }

        return data;
    }
}
