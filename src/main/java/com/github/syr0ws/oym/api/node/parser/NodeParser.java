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

package com.github.syr0ws.oym.api.node.parser;

import com.github.syr0ws.oym.api.node.ObjectNode;

import java.util.Map;

public interface NodeParser {

    /**
     * Parses a map of objects into an ObjectNode that can be manipulated by the system.
     * @param data the data to be parsed.
     * @return an ObjectNode that represents the map passed as parameter.
     * @throws NodeParsingException When no Node can be found for an object type. This is especially the
     * case when the Map contains an Object which neither a primitive wrapper nor a collection nor a
     * Map<String, Object>.
     */
    ObjectNode parse(Map<String, Object> data) throws NodeParsingException;
}
