package com.github.syr0ws.oym.api.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractNode implements YamlNode {

    private final List<String> comments = new ArrayList<>();

    @Override
    public void setComments(List<String> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
    }

    @Override
    public List<String> getComments() {
        return Collections.unmodifiableList(this.comments);
    }
}
