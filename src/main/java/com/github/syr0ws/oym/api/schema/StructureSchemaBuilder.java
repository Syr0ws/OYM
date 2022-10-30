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

package com.github.syr0ws.oym.api.schema;

/**
 * Analyses the content of a class and represents it as an internal structure
 * manipulable by the program.
 */
public interface StructureSchemaBuilder {

    /**
     * Build a schema for a specific object type. A schema contains metadata about an object
     * type which will be used to get fields and assign their values during the mapping process.
     * All these fields must be annotated with the @Key annotation.
     * @param type a type of object to build the schema of.
     * @return the schema of the object.
     */
    <T> StructureSchema<T> build(Class<T> type);
}
