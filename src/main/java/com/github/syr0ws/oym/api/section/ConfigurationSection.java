package com.github.syr0ws.oym.api.section;

import com.github.syr0ws.oym.api.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

public interface ConfigurationSection {

    String getString(@NotNull String path) throws ConfigurationSectionException;

    String getString(@NotNull String path, String defaultValue);

    boolean getBoolean(@NotNull String path) throws ConfigurationSectionException;

    boolean getBoolean(@NotNull String path, boolean defaultValue);

    int getInt(@NotNull String path) throws ConfigurationSectionException;

    int getInt(@NotNull String path, int defaultValue);

    long getLong(@NotNull String path) throws ConfigurationSectionException;

    long getLong(@NotNull String path, long defaultValue);

    float getFloat(@NotNull String path) throws ConfigurationSectionException;

    float getFloat(@NotNull String path, float defaultValue);

    double getDouble(@NotNull String path) throws ConfigurationSectionException;

    double getDouble(@NotNull String path, double defaultValue);

    ConfigurationSection getSection(@NotNull String path) throws ConfigurationSectionException;

    <T> void to(@NotNull T value) throws ConfigurationSectionException;

    <T> T from(@NotNull Class<T> type) throws ConfigurationSectionException;

    boolean hasKey(@NotNull String path);

    ObjectNode getNode();
}
