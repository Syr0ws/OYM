package com.github.syr0ws.oym;

import com.github.syr0ws.oym.api.ObjectFileMapper;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.yaml.ObjectYamlMapper;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws NodeParsingException, TypeAdaptationException {

        // C'est la ConfigurationSection qui permettra de cacher l'encapsulation
        // des adapters pour obtenir des objets à partir des ObjectNode.

        InputStream stream = Main.class.getClassLoader().getResourceAsStream("config.yml");

        ObjectFileMapper mapper = new ObjectYamlMapper();
        mapper.getInstanceProviderModel().addProvider(Team.class, node -> new TeamImpl());

        ObjectNode node = mapper.read(stream);

        TypeAdapter<Player> adapter = mapper.getTypeAdapterFactory().getAdapter(Player.class);

        Player player = adapter.read(node);

        System.out.println(player);

        node = (ObjectNode) adapter.write(player);

        String content = mapper.write(node);

        System.out.println(content);
    }
}
