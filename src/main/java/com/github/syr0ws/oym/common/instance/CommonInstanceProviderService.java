package com.github.syr0ws.oym.common.instance;

import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import com.github.syr0ws.oym.api.node.YamlNode;

public class CommonInstanceProviderService implements InstanceProviderService {

    @Override
    public <T> T provide(Class<T> type, YamlNode node) throws InstantiationException {

        T instance;

        try {
            instance = type.newInstance();
        } catch (IllegalAccessException exception) {
            throw new InstantiationException(String.format("Cannot instantiate type '%s'.", type.getName()));
        }

        return instance;
    }
}
