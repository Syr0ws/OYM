package com.github.syr0ws.oym;

import com.github.syr0ws.oym.api.ObjectFileMapper;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.api.section.ConfigurationSection;
import com.github.syr0ws.oym.api.section.ConfigurationSectionException;
import com.github.syr0ws.oym.yaml.ObjectYamlMapper;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws NodeParsingException, ConfigurationSectionException {

        /*
        Une méthode d'une ConfigurationSection sans default value throw une exception
        si la clé n'existe pas.
         */

        InputStream stream = Main.class.getClassLoader().getResourceAsStream("config.yml");

        ObjectFileMapper mapper = new ObjectYamlMapper();
        mapper.getInstanceProviderModel().addProvider(Team.class, node -> new TeamImpl());

        ConfigurationSection section = mapper.readAsSection(stream);

        Player player = null;

        try { player = section.from(Player.class);
        } catch (ConfigurationSectionException exception) { exception.printStackTrace(); }

        System.out.println(player);

        try { section.to(player);
        } catch (ConfigurationSectionException exception) { exception.printStackTrace(); }

        String content = mapper.writeSection(section);

        System.out.println(content);

        ConfigurationSection teamSection = section.getSection("team");

        System.out.println(teamSection.getNode().getProperties());
    }
}
