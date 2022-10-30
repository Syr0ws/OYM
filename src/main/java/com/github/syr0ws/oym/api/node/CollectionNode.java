package com.github.syr0ws.oym.api.node;

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
