package com.github.syr0ws.oym.api.instance;

import com.github.syr0ws.oym.api.node.Node;

public interface InstanceProvider<I> {

    <T extends I> T provide(Node node) throws Exception;

    Class<I> getType();
}
