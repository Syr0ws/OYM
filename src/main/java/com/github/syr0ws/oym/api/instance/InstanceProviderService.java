package com.github.syr0ws.oym.api.instance;

import com.github.syr0ws.oym.api.node.YamlNode;

public interface InstanceProviderService {

    <T> T provide(Class<T> type, YamlNode node) throws InstantiationException;
}
