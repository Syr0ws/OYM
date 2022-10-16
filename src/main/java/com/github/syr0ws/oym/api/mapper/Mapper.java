package com.github.syr0ws.oym.api.mapper;

import com.github.syr0ws.oym.api.node.ObjectNode;

public interface Mapper<T> {

    T map(ObjectNode node);
}
