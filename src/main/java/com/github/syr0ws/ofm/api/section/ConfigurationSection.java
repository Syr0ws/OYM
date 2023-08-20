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

package com.github.syr0ws.ofm.api.section;

import com.github.syr0ws.ofm.api.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

public interface ConfigurationSection {

    /**
     * Get a String by path.
     * @param path Path to the String to get.
     * @return Requested String.
     * @throws ConfigurationSectionException If the path does not exist or does not represent a String.
     */
    String getString(@NotNull String path) throws ConfigurationSectionException;

    /**
     * Get a String by path.
     * If the path doesn't exist or does not represent a String, a default value is returned.
     * @param path Path to the String to get.
     * @param defaultValue Default value to return if the path doesn't exist or is not a String.
     * @return Requested String.
     */
    String getString(@NotNull String path, String defaultValue);

    /**
     * Get a boolean by path.
     * @param path Path to the boolean to get.
     * @return Requested boolean.
     * @throws ConfigurationSectionException If the path does not exist or does not represent a boolean.
     */
    boolean getBoolean(@NotNull String path) throws ConfigurationSectionException;

    /**
     * Get a boolean by path.
     * If the path doesn't exist or does not represent a boolean, a default value is returned.
     * @param path Path to the boolean to get.
     * @param defaultValue Default value to return if the path doesn't exist or is not a boolean.
     * @return Requested boolean.
     */
    boolean getBoolean(@NotNull String path, boolean defaultValue);

    /**
     * Get an int by path.
     * @param path Path to the int to get.
     * @return Requested int.
     * @throws ConfigurationSectionException If the path does not exist or does not represent an int.
     */
    int getInt(@NotNull String path) throws ConfigurationSectionException;

    /**
     * Get an int by path.
     * If the path doesn't exist or does not represent an int, a default value is returned.
     * @param path Path to the int to get.
     * @param defaultValue Default value to return if the path doesn't exist or is not an int.
     * @return Requested int.
     */
    int getInt(@NotNull String path, int defaultValue);

    /**
     * Get a long by path.
     * @param path Path to the long to get.
     * @return Requested long.
     * @throws ConfigurationSectionException If the path does not exist or does not represent a long.
     */
    long getLong(@NotNull String path) throws ConfigurationSectionException;

    /**
     * Get a long by path.
     * If the path doesn't exist or does not represent a long, a default value is returned.
     * @param path Path to the long to get.
     * @param defaultValue Default value to return if the path doesn't exist or is not a long.
     * @return Requested long.
     */
    long getLong(@NotNull String path, long defaultValue);

    /**
     * Get a double by path.
     * @param path Path to the double to get.
     * @return Requested double.
     * @throws ConfigurationSectionException If the path does not exist or does not represent a double.
     */
    double getDouble(@NotNull String path) throws ConfigurationSectionException;

    /**
     * Get a double by path.
     * If the path doesn't exist or does not represent a double, a default value is returned.
     * @param path Path to the double to get.
     * @param defaultValue Default value to return if the path doesn't exist or is not a double.
     * @return Requested double.
     */
    double getDouble(@NotNull String path, double defaultValue);

    /**
     * Get an element (object or primitive) by path.
     * @param path Path to the element to get.
     * @return Requested element.
     * @throws ConfigurationSectionException If the path does not exist or an error occurred.
     */
    <T> T get(@NotNull String path, Class<T> type) throws ConfigurationSectionException;

    /**
     * Get an element (object or primitive) by path.
     * If the path doesn't exist or an error occurred, a default value is returned.
     * @param path Path to the element to get.
     * @param defaultValue Default value to return if the path doesn't exist or if an error occurred.
     * @return Requested element.
     */
    <T> T get(@NotNull String path, Class<T> type, T defaultValue);

    /**
     * Get a deeper ConfigurationSection by path.
     * @param path Path to the ConfigurationSection to get.
     * @return Requested ConfigurationSection.
     * @throws ConfigurationSectionException If the path does not exist or does not represent a ConfigurationSection.
     */
    ConfigurationSection getSection(@NotNull String path) throws ConfigurationSectionException;

    /**
     * Convert an object to the section. This operation overrides all the existing keys in the section.
     * @param value Object to convert.
     * @throws ConfigurationSectionException If an error occurs during the conversion.
     */
    <T> void set(@NotNull T value) throws ConfigurationSectionException;

    /**
     * Convert the current section to an object.
     * @param type Object type to convert the section.
     * @return Converted object.
     * @throws ConfigurationSectionException If an error occurs during the conversion.
     */
    <T> T getAsObject(@NotNull Class<T> type) throws ConfigurationSectionException;

    /**
     * Check if a value is set for the given path.
     * @param path Path to check.
     * @return true if a value is set at the specified path or else false.
     */
    boolean hasKey(@NotNull String path);

    /**
     * Get the ObjectNode manipulated by the ConfigurationSection.
     * @return ObjectNode used in the section.
     */
    ObjectNode getNode();
}
