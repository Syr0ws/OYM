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

package com.github.syr0ws.ofm.api.node;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Represents a collection.
 */
public class CollectionNode extends AbstractNode implements Iterable<Node> {

    private final Collection<Node> nodes;

    public CollectionNode(@NotNull Collection<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public Object get() {
        return this.getNodes();
    }

    @NotNull
    @Override
    public Iterator<Node> iterator() {
        return this.nodes.iterator();
    }

    public Stream<Node> stream() {
        return this.nodes.stream();
    }

    public Collection<Node> getNodes() {
        return Collections.unmodifiableCollection(this.nodes);
    }
}
