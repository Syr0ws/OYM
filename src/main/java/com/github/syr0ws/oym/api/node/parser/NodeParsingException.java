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

package com.github.syr0ws.oym.api.node.parser;

/**
 * Exception raised when an error occurs while parsing objects into nodes.
 */
public class NodeParsingException extends Exception {

    public NodeParsingException(String message) {
        super(message);
    }

    public NodeParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeParsingException(Throwable cause) {
        super(cause);
    }
}
