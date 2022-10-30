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

package com.github.syr0ws.ofm;

import com.github.syr0ws.ofm.api.ObjectFileMapper;
import com.github.syr0ws.ofm.api.node.parser.NodeParsingException;
import com.github.syr0ws.ofm.api.section.ConfigurationSection;
import com.github.syr0ws.ofm.api.section.ConfigurationSectionException;
import com.github.syr0ws.ofm.yaml.ObjectYamlMapper;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws NodeParsingException, ConfigurationSectionException {

        InputStream stream = Main.class.getClassLoader().getResourceAsStream("config.yml");

        ObjectFileMapper mapper = new ObjectYamlMapper();
        mapper.getInstanceProviderModel().addProvider(Team.class, node -> new TeamImpl());

        ConfigurationSection section = mapper.readSection(stream);

        Player player = null;

        try { player = section.get(Player.class);
        } catch (ConfigurationSectionException exception) { exception.printStackTrace(); }

        System.out.println(player);

        try { section.set(player);
        } catch (ConfigurationSectionException exception) { exception.printStackTrace(); }

        String content = mapper.writeSection(section);

        System.out.println(content);

        ConfigurationSection teamSection = section.getSection("team");

        System.out.println(teamSection);
    }
}
