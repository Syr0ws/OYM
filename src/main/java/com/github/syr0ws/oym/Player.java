package com.github.syr0ws.oym;

import com.github.syr0ws.oym.api.annotation.Key;
import com.github.syr0ws.oym.api.annotation.KeySetter;

import java.util.Map;

public class Player {

    @Key(comments = {"name"})
    private String name;

    @Key(comments = {"points"})
    private int points;

    @Key(comments = {"team"})
    private Team team;

    @Key(comments = {"test"})
    private Map<String, String> test;

    @Key(comments = {"score"})
    private double score;

    @Key(comments = {"admin"})
    private boolean admin;

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
