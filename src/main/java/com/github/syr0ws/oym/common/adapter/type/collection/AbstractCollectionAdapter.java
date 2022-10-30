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

package com.github.syr0ws.oym.common.adapter.type.collection;

import com.github.syr0ws.oym.api.adapter.TypeAdaptationException;
import com.github.syr0ws.oym.api.adapter.TypeAdapter;
import com.github.syr0ws.oym.api.node.CollectionNode;
import com.github.syr0ws.oym.api.node.Node;
import com.github.syr0ws.oym.common.util.NodeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCollectionAdapter<T extends Collection<E>, E> implements TypeAdapter<T> {

    private final TypeAdapter<E> adapter;

    public AbstractCollectionAdapter(@NotNull TypeAdapter<E> adapter) {
        this.adapter = adapter;
    }

    public abstract T getInstance();

    @Override
    public T read(Node node) throws TypeAdaptationException {

        CollectionNode collectionNode = NodeUtil.cast(node, CollectionNode.class);

        T collection = this.getInstance();

        for(Node internal : collectionNode) {

            E object = this.adapter.read(internal);

            collection.add(object);
        }

        return collection;
    }

    @Override
    public Node write(T collection) throws TypeAdaptationException {

        List<Node> nodes = new ArrayList<>();

        for(E object : collection) {

            Node node = this.adapter.write(object);

            nodes.add(node);
        }

        return new CollectionNode(nodes);
    }
}
