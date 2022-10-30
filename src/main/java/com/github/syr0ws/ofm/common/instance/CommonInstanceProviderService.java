/*
 *    Copyright 2022 syr0ws
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.syr0ws.ofm.common.instance;

import com.github.syr0ws.ofm.api.instance.InstanceException;
import com.github.syr0ws.ofm.api.instance.InstanceProvider;
import com.github.syr0ws.ofm.api.instance.InstanceProviderModel;
import com.github.syr0ws.ofm.api.instance.InstanceProviderService;
import com.github.syr0ws.ofm.api.node.Node;
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
