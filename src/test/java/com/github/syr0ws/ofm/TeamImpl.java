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

import com.github.syr0ws.ofm.api.annotation.Property;

import java.util.ArrayList;
import java.util.Objects;

public class TeamImpl implements Team {

    @Property
    private String name;

    @Property
    private TeamColor color;

    @Property
    private ArrayList<String> members;

    public TeamImpl() {
    }

    public TeamImpl(String name, TeamColor color, ArrayList<String> members) {
        this.name = name;
        this.color = color;
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamImpl team = (TeamImpl) o;
        return Objects.equals(name, team.name) && color == team.color && Objects.equals(members, team.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, members);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", members=" + members +
                '}';
    }
}
