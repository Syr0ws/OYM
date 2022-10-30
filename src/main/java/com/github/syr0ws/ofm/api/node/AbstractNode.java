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

package com.github.syr0ws.ofm.api.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractNode implements Node {

    private final List<String> comments = new ArrayList<>();

    @Override
    public void setComments(List<String> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
    }

    @Override
    public List<String> getComments() {
        return Collections.unmodifiableList(this.comments);
    }
}
