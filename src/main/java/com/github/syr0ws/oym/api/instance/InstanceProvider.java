package com.github.syr0ws.oym.api.instance;

import com.github.syr0ws.oym.api.node.YamlNode;

public interface InstanceProvider<I> {

    <T extends I> T provide(YamlNode node) throws Exception;

    Class<I> getType();
}
