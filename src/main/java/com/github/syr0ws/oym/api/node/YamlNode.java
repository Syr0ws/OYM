package com.github.syr0ws.oym.api.node;

import java.util.List;

public interface YamlNode {

    boolean isObject();

    boolean isCollection();

    Object get();

    void setComments(List<String> comments);

    List<String> getComments();
}
