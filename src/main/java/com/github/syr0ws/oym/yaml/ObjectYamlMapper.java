/*
 *    Copyright 2022 syr0ws
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.syr0ws.oym.yaml;

import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.node.parser.NodeParser;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.common.AbstractObjectFileMapper;
import com.github.syr0ws.oym.common.node.parser.CommonNodeParser;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.MappingNode;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

public class ObjectYamlMapper extends AbstractObjectFileMapper {

    private final Yaml yaml;
    private final YamlMapper mapper;

    public ObjectYamlMapper() {

        DumperOptions options = new DumperOptions();
        options.setProcessComments(true);

        this.yaml = new Yaml(options);
        this.mapper = new YamlMapper();
    }

    @Override
    public ObjectNode readNode(InputStream stream) throws NodeParsingException {

        Map<String, Object> map = this.yaml.load(stream);

        NodeParser parser = new CommonNodeParser();

        return parser.parse(map);
    }

    @Override
    public String writeNode(ObjectNode node) {

        MappingNode mappingNode = this.mapper.map(node);

        StringWriter writer = new StringWriter();

        this.yaml.serialize(mappingNode, writer);

        return writer.toString();
    }
}
