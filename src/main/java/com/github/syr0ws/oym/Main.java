package com.github.syr0ws.oym;

import com.github.syr0ws.oym.api.ObjectFileMapper;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.api.section.ConfigurationSection;
import com.github.syr0ws.oym.yaml.ObjectYamlMapper;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws NodeParsingException {

        InputStream stream = Main.class.getClassLoader().getResourceAsStream("config.yml");

        ObjectFileMapper mapper = new ObjectYamlMapper();
        mapper.getInstanceProviderModel().addProvider(Team.class, node -> new TeamImpl());

        ConfigurationSection section = mapper.readAsSection(stream);

        Player player = section.from(Player.class);

        System.out.println(player);

        section.to(player);

        String content = mapper.writeSection(section);

        System.out.println(content);
    }
}
