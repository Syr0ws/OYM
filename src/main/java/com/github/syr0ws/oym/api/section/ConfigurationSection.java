package com.github.syr0ws.oym.api.section;

import com.github.syr0ws.oym.api.node.ObjectNode;

public interface ConfigurationSection {

    <T> void to(T value);

    <T> T from(Class<T> type);

    ObjectNode getNode();
}
