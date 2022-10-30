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

package com.github.syr0ws.ofm.common.adapter.type.map;

import com.github.syr0ws.ofm.api.adapter.TypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class HashMapAdapter<V> extends AbstractMapAdapter<HashMap<String, V>, V> {

    public HashMapAdapter(@NotNull TypeAdapter<V> adapter) {
        super(adapter);
    }

    @Override
    public HashMap<String, V> getMapInstance() {
        return new HashMap<>();
    }
}
