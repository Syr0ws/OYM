package com.github.syr0ws.ofm.common.section;

import com.github.syr0ws.ofm.Player;
import com.github.syr0ws.ofm.Team;
import com.github.syr0ws.ofm.TeamColor;
import com.github.syr0ws.ofm.TeamImpl;
import com.github.syr0ws.ofm.api.ObjectFileMapper;
import com.github.syr0ws.ofm.api.node.parser.NodeParsingException;
import com.github.syr0ws.ofm.api.section.ConfigurationSection;
import com.github.syr0ws.ofm.api.section.ConfigurationSectionException;
import com.github.syr0ws.ofm.yaml.ObjectYamlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;

class NodeSectionTest {

    private static ConfigurationSection section;
    private static Player expectedPlayer;
    private static Team expectedTeam;

    @BeforeAll
    public static void loadConfig() throws NodeParsingException {

        // Loading configuration.
        InputStream stream = NodeSectionTest.class.getClassLoader().getResourceAsStream("config.yml");

        ObjectFileMapper mapper = new ObjectYamlMapper();
        mapper.getInstanceProviderModel().addProvider(Team.class, node -> new TeamImpl());

        section = mapper.readSection(stream);

        // Creating reference data.
        ArrayList<String> members = new ArrayList<>();
        members.add("a");
        members.add("b");
        members.add("c");

        expectedTeam = new TeamImpl("Bleu", TeamColor.BLUE, members);
        expectedPlayer = new Player("John", 150, expectedTeam, 5.5d, true);
    }

    @Test
    void getString() {
        Assertions.assertEquals("John", section.getString("name", ""));
        Assertions.assertEquals("Bleu", section.getString("team.name", ""));
    }

    @Test
    void getBoolean() {
        Assertions.assertTrue(section.getBoolean("admin", false));
    }

    @Test
    void getInt() {
        Assertions.assertEquals(150, section.getInt("points", 0));
    }

    @Test
    void getLong() {
        Assertions.assertEquals(1692562524045L, section.getLong("time", 0));
    }

    @Test
    void getDouble() {
        Assertions.assertEquals(5.5, section.getDouble("score", 0));
    }

    @Test
    void get() {
        Assertions.assertEquals("John", section.get("name", String.class, ""));
        Assertions.assertTrue(section.get("admin", Boolean.class, false));
        Assertions.assertEquals(150, section.get("points", Integer.class, 0));
    }

    @Test
    void getSection() {

        try {
            ConfigurationSection teamSection = section.getSection("team");
            Assertions.assertNotNull(teamSection);
        } catch (ConfigurationSectionException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void set() {

    }

    @Test
    void getAsObject() {

        try {
            Player player = section.getAsObject(Player.class);
            Assertions.assertEquals(expectedPlayer, player);
        } catch (ConfigurationSectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void hasKey() {
        Assertions.assertTrue(section.hasKey("team"));
        Assertions.assertTrue(section.hasKey("team.name"));
        Assertions.assertFalse(section.hasKey("hello"));
    }
}