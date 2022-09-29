package com.github.syr0ws.oym;

import com.github.syr0ws.oym.api.annotation.Key;
import com.github.syr0ws.oym.api.annotation.KeySetter;

import java.util.Map;

public class Player {

    @Key(comments = {"", "", ""})
    private String name;

    @Key
    private Integer points;

    @Key
    private Team team;

    @Key
    private Map<String, String> test;

    @KeySetter(field = "name")
    public void setName(String name) {
        System.out.println(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", team=" + team +
                ", test=" + test +
                '}';
    }
}
