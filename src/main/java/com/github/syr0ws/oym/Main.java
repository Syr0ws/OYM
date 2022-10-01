package com.github.syr0ws.oym;

import com.github.syr0ws.oym.api.YamlObjectParser;
import com.github.syr0ws.oym.api.YamlObjectParsingException;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import com.github.syr0ws.oym.api.node.YamlObject;
import com.github.syr0ws.oym.api.schema.StructureSchemaBuilder;
import com.github.syr0ws.oym.common.CommonYamlObjectParser;
import com.github.syr0ws.oym.common.adapter.TypeAdapterFactoryProvider;
import com.github.syr0ws.oym.common.instance.CommonInstanceProviderService;
import com.github.syr0ws.oym.common.schema.CommonStructureSchemaBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws YamlObjectParsingException, TypeAdaptationException {

        InputStream stream = Main.class.getClassLoader().getResourceAsStream("config.yml");

        Yaml yaml = new Yaml();

        Map<String, Object> map = yaml.load(stream);

        YamlObjectParser parser = new CommonYamlObjectParser();
        YamlObject object = parser.parse(map);

        StructureSchemaBuilder builder = new CommonStructureSchemaBuilder();
        InstanceProviderService service = new CommonInstanceProviderService();

        TypeAdapterFactory factory = new TypeAdapterFactoryProvider(builder, service);

        TypeAdapter<Player> adapter = factory.getAdapter(Player.class);

        Player player = adapter.read(object);

        System.out.println(player);

        TypeAdapter<Team> teamAdapter = factory.getAdapter(Team.class);

        Team team = teamAdapter.read(object.getProperty("team"));

        System.out.println(team);

        YamlObject node = (YamlObject) adapter.write(player);

        System.out.println(node.getProperties());
    }
}
