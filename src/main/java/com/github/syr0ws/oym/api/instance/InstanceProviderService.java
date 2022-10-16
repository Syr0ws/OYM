package com.github.syr0ws.oym.api.instance;

import com.github.syr0ws.oym.api.node.Node;

public interface InstanceProviderService {

    <T> T provide(Class<T> type, Node node) throws InstantiationException;
}
