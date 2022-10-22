package com.github.syr0ws.oym.common.instance;

import com.github.syr0ws.oym.api.instance.InstanceException;
import com.github.syr0ws.oym.api.instance.InstanceProvider;
import com.github.syr0ws.oym.api.instance.InstanceProviderModel;
import com.github.syr0ws.oym.api.instance.InstanceProviderService;
import com.github.syr0ws.oym.api.node.Node;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CommonInstanceProviderService implements InstanceProviderService {

    private final InstanceProviderModel model;

    public CommonInstanceProviderService(@NotNull InstanceProviderModel model) {
        this.model = model;
    }

    @Override
    public <T> T getInstance(Class<T> type, Node node) throws InstanceException {

        Optional<InstanceProvider<T>> optional = this.model.getProvider(type);

        T instance;

        if(optional.isPresent()) {

            InstanceProvider<T> provider = optional.get();
            instance = provider.provide(node);

        } else instance = this.createInstance(type);

        return instance;
    }

    private <T> T createInstance(Class<T> type) throws InstanceException {

        T instance;

        try {
            instance = type.newInstance();
        } catch (IllegalAccessException | InstantiationException exception) {
            throw new InstanceException(String.format("Cannot instantiate type '%s'.", type.getName()));
        }

        return instance;
    }
}
