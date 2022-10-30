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

package com.github.syr0ws.ofm.api.instance;

import com.github.syr0ws.ofm.api.node.Node;

/**
 * Provides instances depending on types during the mapping process. It should be
 * able to use an InstanceProvider or to create it using reflection.
 */
public interface InstanceProviderService {

    /**
     * Provide an instance of Class<T> depending on a context node. The context node
     * represents the node which will be converted to an object of type T during the
     * mapping process.
     * @param type a type of object to get the instance of.
     * @param node a context node.
     * @return an object of type T.
     * @throws InstanceException if an error occurs during the operation, especially when the
     * instance is created manually using reflection.
     */
    <T> T getInstance(Class<T> type, Node node) throws InstanceException;
}
