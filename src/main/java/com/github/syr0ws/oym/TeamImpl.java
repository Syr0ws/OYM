package com.github.syr0ws.oym;

import com.github.syr0ws.oym.api.annotation.Key;

import java.util.ArrayList;

public class TeamImpl implements Team {

    @Key
    private String name;

    @Key
    private TeamColor color;

    @Key
    private ArrayList<String> members;

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", members=" + members +
                '}';
    }
}
