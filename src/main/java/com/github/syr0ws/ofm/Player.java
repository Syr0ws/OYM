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
import com.github.syr0ws.ofm.api.annotation.PropertySetter;

import java.util.Map;

public class Player {

    @Property(comments = {"name"})
    private String name;

    @Property(comments = {"points"})
    private int points;

    @Property(comments = {"team"})
    private Team team;

    @Property(comments = {"test"})
    private Map<String, String> test;

    @Property(comments = {"score"})
    private double score;

    @Property(comments = {"admin"})
    private boolean admin;

    @PropertySetter(field = "name")
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
