package com.github.syr0ws.oym;

import com.github.syr0ws.oym.api.adapter.provider.TypeAdapterProviderModel;
import com.github.syr0ws.oym.api.node.parser.NodeParser;
import com.github.syr0ws.oym.api.node.parser.NodeParsingException;
import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.adapter.TypeAdapterFactory;
import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import com.github.syr0ws.oym.api.node.ObjectNode;
import com.github.syr0ws.oym.api.schema.StructureSchemaBuilder;
import com.github.syr0ws.oym.common.adapter.TypeAdapterFactoryProvider;
import com.github.syr0ws.oym.common.adapter.provider.CommonTypeAdapterProviderModel;
import com.github.syr0ws.oym.common.adapter.provider.type.*;
import com.github.syr0ws.oym.common.adapter.provider.type.collection.ArrayListAdapterProvider;
import com.github.syr0ws.oym.common.adapter.provider.type.collection.ListAdapterProvider;
import com.github.syr0ws.oym.common.adapter.provider.type.collection.SetAdapterProvider;
import com.github.syr0ws.oym.common.adapter.provider.type.map.HashMapAdapterProvider;
import com.github.syr0ws.oym.common.adapter.provider.type.map.MapAdapterProvider;
import com.github.syr0ws.oym.common.node.parser.CommonNodeParser;
import com.github.syr0ws.oym.common.instance.CommonInstanceProviderService;
import com.github.syr0ws.oym.common.schema.CommonStructureSchemaBuilder;
import com.github.syr0ws.oym.yaml.YamlMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.MappingNode;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws NodeParsingException, TypeAdaptationException {

        InputStream stream = Main.class.getClassLoader().getResourceAsStream("config.yml");

        DumperOptions options = new DumperOptions();
        options.setProcessComments(true);
        Yaml yaml = new Yaml(options);

        Map<String, Object> map = yaml.load(stream);

        NodeParser parser = new CommonNodeParser();
        ObjectNode object = parser.parse(map);

        StructureSchemaBuilder builder = new CommonStructureSchemaBuilder();
        InstanceProviderService service = new CommonInstanceProviderService();

        // TypeAdapterFactory factory = new TypeAdapterFactoryProvider(builder, service);

        TypeAdapterProviderModel typeAdapterProviderModel = new CommonTypeAdapterProviderModel();
        typeAdapterProviderModel.addProvider(new StringAdapterProvider());
        typeAdapterProviderModel.addProvider(new IntegerAdapterProvider());
        typeAdapterProviderModel.addProvider(new DoubleAdapterProvider());
        typeAdapterProviderModel.addProvider(new LongAdapterProvider());
        typeAdapterProviderModel.addProvider(new UUIDAdapterProvider());
        typeAdapterProviderModel.addProvider(new EnumTypeAdapterProvider<>());
        typeAdapterProviderModel.addProvider(new ListAdapterProvider<>());
        typeAdapterProviderModel.addProvider(new ArrayListAdapterProvider<>());
        typeAdapterProviderModel.addProvider(new SetAdapterProvider<>());
        typeAdapterProviderModel.addProvider(new HashMapAdapterProvider<>());
        typeAdapterProviderModel.addProvider(new MapAdapterProvider<>());
        typeAdapterProviderModel.addProvider(new HashMapAdapterProvider<>());

        TypeAdapterFactory factory = new TypeAdapterFactoryProvider(typeAdapterProviderModel, service, builder);

        TypeAdapter<Player> adapter = factory.getAdapter(Player.class);

        Player player = adapter.read(object);

        System.out.println(player);

        TypeAdapter<Team> teamAdapter = factory.getAdapter(Team.class);

        Team team = teamAdapter.read(object.getProperty("team"));

        System.out.println(team);

        ObjectNode node = (ObjectNode) adapter.write(player);

        YamlMapper mapper = new YamlMapper();

        MappingNode mappingNode = mapper.map(node);

        StringWriter writer = new StringWriter();

        yaml.serialize(mappingNode, writer);

        System.out.println(writer);
    }
}
