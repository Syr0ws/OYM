package com.github.syr0ws.oym.api.node;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

public class YamlCollection extends AbstractNode implements Iterable<YamlNode> {

    private final Collection<YamlNode> nodes;

    public YamlCollection(@NotNull Collection<YamlNode> nodes) {
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
    public Iterator<YamlNode> iterator() {
        return this.nodes.iterator();
    }

    public Stream<YamlNode> stream() {
        return this.nodes.stream();
    }

    public Collection<YamlNode> getNodes() {
        return Collections.unmodifiableCollection(this.nodes);
    }
}
